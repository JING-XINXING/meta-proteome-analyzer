package de.mpa.model.taxonomy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.mpa.client.Client;
import de.mpa.client.settings.ParameterMap;
import de.mpa.db.mysql.accessor.Taxonomy;
import de.mpa.model.analysis.UniProtUtilities;
import de.mpa.model.dbsearch.PeptideHit;
import de.mpa.model.dbsearch.ProteinHit;

/**
 * This class serves as utility class for various methods handling taxonomic issues.
 * 
 * @author T. Muth, A. Behne
 *
 */
public class TaxonomyUtils {

	/**
	 * Enumeration holding taxonomy definition-related constants.
	 * @author A. Behne
	 */
	public enum TaxonomyDefinition {
		COMMON_ANCESTOR("by common ancestor") {
			@Override
			public TaxonomyNode getCommonTaxonomyNode(
					TaxonomyNode nodeA, TaxonomyNode nodeB,
					HashMap<Long, Taxonomy> taxonomyMap,
					HashMap<Long, TaxonomyNode> taxonomyNodeMap) {

				// Get root paths of both taxonomy nodes
				TaxonomyNode[] path1 = nodeA.getPath();
				TaxonomyNode[] path2 = nodeB.getPath();
				TaxonomyNode ancestor = null;

				// Find last common element starting from the root
				int len = Math.min(path1.length, path2.length);

				// Only root
				if (len == 0) {
					// is root
					ancestor = taxonomyNodeMap.get(1L);
				}

				// Taxonomy is superkingdom or "unclassified" (taxID == "0") Unclassified
				if (len == 1){
					// Both are unclassified
					if (nodeA.getID() == 0 && nodeB.getID() == 0) {
						ancestor = taxonomyNodeMap.get(0L);
						//						ancestor = new TaxonomyNode(0, UniProtUtilities.TaxonomyRank.NO_RANK, "Unclassified");
					}
					// Just one is unclassified (Taxonomy ID is "0")
					else if (nodeA.getID() == 0){
						ancestor = nodeB;
					}
					// Just one is unclassified (Taxonomy ID is "0")
					else if (nodeB.getID() == 0){
						ancestor = nodeA;
						// Similar superkingdom
					} else if (path1[0].equals(path2[0])){
						ancestor = path1[0];
						// Else different superkingdoms
					} else if (!path1[0].equals(path2[0])){
						ancestor = new TaxonomyNode(1, UniProtUtilities.TaxonomyRank.NO_RANK, "root");
					}
				}
				// Find common ancestor of both paths
				if (len>1) {
					// check for different superkingdoms
					if (!path1[0].equals(path2[0])) {
						ancestor = new TaxonomyNode(1, UniProtUtilities.TaxonomyRank.NO_RANK, "root");
					} else {
						ancestor = path1[0];	// initialize ancestor as root
						for (int i = 1; i < len; i++) {
							if (!path1[i].equals(path2[i])) {
								break;
							}
							ancestor = path1[i];
						}
					}
				}
				if (ancestor == null) {
					System.err.println("Return null during common ancestor calculation!");
				}
				return ancestor;
			}
		},
		MOST_SPECIFIC("by most specific member") {
			@Override
			public TaxonomyNode getCommonTaxonomyNode(
					TaxonomyNode nodeA, TaxonomyNode nodeB,
					HashMap<Long, Taxonomy> taxonomyMap,
					HashMap<Long, TaxonomyNode> taxonomyNodeMap) {
				// Get root paths of both taxonomy nodes
				TaxonomyNode[] path1 = nodeA.getPath();
				TaxonomyNode[] path2 = nodeB.getPath();
				// return node at the end of the longer one of either paths
				if (path1.length >= path2.length) {
					return nodeA;
				} else {
					return nodeB;
				}
			}
		};

		/**
		 * The name string.
		 */
		private final String name;

		/**
		 * Constructs a meta-protein generation rule using the specified name string.
		 * @param name the name of the rule
		 */
		TaxonomyDefinition(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}

		/**
		 * Returns the common taxonomy node of the specified pair of taxonomy nodes.
		 * @param nodeA the first taxonomy node
		 * @param nodeB the second taxonomy node
		 * @return the common taxonomy node
		 */
		public abstract TaxonomyNode getCommonTaxonomyNode(TaxonomyNode nodeA, TaxonomyNode nodeB, HashMap<Long, Taxonomy> taxonomyMap, HashMap<Long, TaxonomyNode> taxonomyNodeMap);
	}

	/**
	 * Private constructor as class contains only static helper methods.
	 */
	private TaxonomyUtils() {}

	/**
	 * This method creates a taxonomy node which contains all ancestor taxonomy nodes up to the root node.
	 * @param currentID Taxonomy ID
	 * @param taxonomyMap Taxonomy Map containing taxonomy DB accessor objects.
	 * @return TaxonomyNode Taxonomy node in the end state.
	 */
	@Deprecated
	public static TaxonomyNode createTaxonomyNode(long currentID, HashMap<Long, Taxonomy> taxonomyMap) {

		Taxonomy current = taxonomyMap.get(currentID);
		Map<String, UniProtUtilities.TaxonomyRank> targetRanks = UniProtUtilities.TAXONOMY_RANKS_MAP;

		// Check for rank being contained in the main categories (from superkingdom to species)
		UniProtUtilities.TaxonomyRank taxonomyRank = targetRanks.get(current.getRank());
		if (taxonomyRank == null) {
			// TODO: Check whether the general category "species" holds true for all available ranks.
			taxonomyRank = UniProtUtilities.TaxonomyRank.SPECIES;
		}

		// Create leaf node
		TaxonomyNode leafNode = new TaxonomyNode(
				(int) current.getTaxonomyid(), taxonomyRank, current.getDescription());

		// Iterate up taxonomic hierarchy and create parent nodes
		TaxonomyNode currentNode = leafNode;
		boolean reachedRoot = false;
		while (!reachedRoot) {
			long parentID = current.getParentid();
			current = taxonomyMap.get(parentID);
			if (current != null) {
				// Check whether we have reached the root already
				reachedRoot = (current.getParentid() == 0L);
				// Check whether parent rank is in targeted ranks
				UniProtUtilities.TaxonomyRank parentRank = targetRanks.get(current.getRank());
				if (parentRank != null) {
					// Create and configure parent node
					TaxonomyNode parentNode = new TaxonomyNode(
							(int) current.getTaxonomyid(), parentRank, current.getDescription());
					currentNode.setParentNode(parentNode);
					// TODO: consider subspecies distinction in database, so far all subspecies are labeled species there (Nov. 2013)
					if (parentRank == UniProtUtilities.TaxonomyRank.SPECIES) {
						currentNode.setRank(UniProtUtilities.TaxonomyRank.SUBSPECIES);
					}
					currentNode = parentNode;
				}
			} else {
				System.err.println("Unknown parent ID: " + parentID);
				break;
			}
		}
		return leafNode;
	}

	/**
	 * Creates a taxonomy node which contains all ancestor taxonomy nodes up to
	 * the root node. Looks up unmapped IDs in the remote database and stores
	 * them in the provided map.
	 * @param currentID the taxonomy ID of the start element
	 * @param taxonomyMap map containing taxonomy ID-to-taxonomy node mappings
	 * @param conn the database connection
	 * @return the desired taxonomy node
	 * @throws SQLException if a database error occurs
	 */
	public static TaxonomyNode createTaxonomyNode(long currentID, HashMap<Long, Taxonomy> taxonomyMap, HashMap<Long, TaxonomyNode> taxonomyNodeMap) {
		// inits
		Map<String, UniProtUtilities.TaxonomyRank> targetRanks = UniProtUtilities.TAXONOMY_RANKS_MAP;
		// check if we have the node already
		if (taxonomyNodeMap.containsKey(currentID)) {
			return taxonomyNodeMap.get(currentID);
		}
		// create leafNode 
		TaxonomyNode taxNode = null;
		Taxonomy currentTaxonomy = taxonomyMap.get(currentID);
		UniProtUtilities.TaxonomyRank taxonomyRank = targetRanks.get(currentTaxonomy.getRank());
		if (taxonomyRank == null) {
			taxonomyRank = UniProtUtilities.TaxonomyRank.SPECIES;
		}
		taxNode = new TaxonomyNode((int) currentTaxonomy.getTaxonomyid(), taxonomyRank, currentTaxonomy.getDescription());
		// Iterate up taxonomic hierarchy and create parent nodes
		TaxonomyNode currentNode = taxNode;
		boolean reachedRoot; // = (currentTaxonomy.getParentid() == 1L);
		if (currentNode.getID() == 1L) {
			// root node, we put itself as its parent??
			//			currentNode.setParentNode(currentNode);
			reachedRoot = true;
		} else {
			reachedRoot = false;
		}
		taxonomyNodeMap.put((long) currentNode.getID(), currentNode);
		while (!reachedRoot) {
			//			long parentID = currentTaxonomy.getParentid();
			// look up taxonomy in map
			currentTaxonomy = taxonomyMap.get(currentTaxonomy.getParentid());
			// Check whether we have reached the root already
			reachedRoot = (currentTaxonomy.getTaxonomyid() == 1L);
			// Check whether parent rank is in targeted ranks
			UniProtUtilities.TaxonomyRank parentRank = targetRanks.get(currentTaxonomy.getRank());
			if (parentRank == null) {
				parentRank = UniProtUtilities.TaxonomyRank.NO_RANK;
			}
			// Create and configure parent node
			TaxonomyNode parentNode = null;
			if (taxonomyNodeMap.containsKey(currentTaxonomy.getTaxonomyid())) {
				parentNode = taxonomyNodeMap.get(currentTaxonomy.getTaxonomyid());
			} else if (currentTaxonomy.getTaxonomyid() == 1L) {
				// create root node if doesnt exist
				parentNode = new TaxonomyNode((int) 1L, null, currentTaxonomy.getDescription());
				taxonomyNodeMap.put(currentTaxonomy.getTaxonomyid(), parentNode);
			} else {
				parentNode = new TaxonomyNode(
						(int) currentTaxonomy.getTaxonomyid(), parentRank, currentTaxonomy.getDescription());
				taxonomyNodeMap.put(currentTaxonomy.getTaxonomyid(), parentNode);
			}
			currentNode.setParentNode(parentNode);
			if (parentRank == UniProtUtilities.TaxonomyRank.SPECIES) {
				currentNode.setRank(UniProtUtilities.TaxonomyRank.SUBSPECIES);
			}

			currentNode = parentNode;
		}

		if (currentNode.getParentNode() == null) {
			currentNode.setParentNode(taxonomyNodeMap.get(1L));
		}
		return taxNode;
	}
	//		try {
	//			Taxonomy current = taxonomyMap.get(currentID);
	//			Map<String, UniProtUtilities.TaxonomyRank> targetRanks = UniProtUtilities.TAXONOMY_RANKS_MAP;
	//
	//			if (current == null) {
	//				current = Taxonomy.findFromTaxID(currentID, conn);
	//				taxonomyMap.put(currentID, current);
	//			}
	//			if (current == null) {
	//				System.err.println("ERROR: TaxID: " + currentID);
	//			} else if (current.getRank() == null) {
	//				System.err.println("ERROR: CurrentTax: " + current.getTaxonomyid());
	//			}
	//			// Check for rank being contained in the main categories (from superkingdom to species)
	//			
	//			UniProtUtilities.TaxonomyRank taxonomyRank = targetRanks.get(current.getRank());
	//			if (taxonomyRank == null) {
	//				// TODO: Check whether the general category "species" holds true for all available ranks.
	//				taxonomyRank = UniProtUtilities.TaxonomyRank.SPECIES;
	//			}
	//
	//			// Create leaf node ERROR: unre
	//			TaxonomyNode leafNode = new TaxonomyNode(
	//					(int) current.getTaxonomyid(), taxonomyRank, current.getDescription());
	//
	//			// Iterate up taxonomic hierarchy and create parent nodes
	//			TaxonomyNode currentNode = leafNode;
	//			boolean reachedRoot = false;
	//			while (!reachedRoot) {
	//				long parentID = current.getParentid();
	//				// look up taxonomy in map
	//				current = taxonomyMap.get(parentID);
	//				// if no mapping exists...
	//				if (current == null) {
	//					// look up taxonomy in database
	//					current = Taxonomy.findFromTaxID(parentID, conn);
	//					// if still no taxonomy could be retrieved...
	//					if (current == null) {
	//						// abort with error message
	//						throw new SQLException("Unknown parent ID: " + parentID);
	//					}
	//					taxonomyMap.put(parentID, current);
	//				}
	//
	//				// Check whether we have reached the root already
	//				reachedRoot = (current.getParentid() == 0L);
	//				// Check whether parent rank is in targeted ranks
	//				UniProtUtilities.TaxonomyRank parentRank = targetRanks.get(current.getRank());
	//				if (parentRank != null) {
	//					// Create and configure parent node
	//					TaxonomyNode parentNode = new TaxonomyNode(
	//							(int) current.getTaxonomyid(), parentRank, current.getDescription());
	//					currentNode.setParentNode(parentNode);
	//					// TODO: consider subspecies distinction in database, so far all subspecies are labeled species there (Nov. 2013)
	//					if (parentRank == UniProtUtilities.TaxonomyRank.SPECIES) {
	//						currentNode.setRank(UniProtUtilities.TaxonomyRank.SUBSPECIES);
	//					}
	//					currentNode = parentNode;
	//				}
	//			}
	//			return leafNode;
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//	}

	/**
	 * Method to go through a peptide set and define for each peptide hit the
	 * common taxonomy of the subsequent proteins.
	 * @param peptideSet the peptide set
	 */
	public static void determinePeptideTaxonomy(ArrayList<PeptideHit> peptideSet, TaxonomyUtils.TaxonomyDefinition definition, HashMap<Long, Taxonomy> taxonomyMap, HashMap<Long, TaxonomyNode> taxonomyNodeMap) {
		for (PeptideHit peptideHit : peptideSet) {
			// iterate proteins
			ArrayList<TaxonomyNode> taxonNodes = new ArrayList<TaxonomyNode>();
			// extract child taxonomy nodes (in this case proteins are children)
			for (ProteinHit protein : peptideHit.getProteinHits()) {
				taxonNodes.add(protein.getTaxonomyNode());
			}
			// calculate ancestor
			TaxonomyNode ancestor = taxonNodes.get(0);
			for (TaxonomyNode taxNode : taxonNodes) {
				ancestor = definition.getCommonTaxonomyNode(ancestor, taxNode, taxonomyMap, taxonomyNodeMap);
			}
			// set common taxonomy node
			peptideHit.setTaxonomyNode(ancestor);
			Client.getInstance().firePropertyChange("progressmade", false, true);
		}
	}
		
		
		
		//		// Map with taxonomy entries used to merge redundant nodes
		//		Map<Integer, TaxonomyNode> nodeMap = new HashMap<Integer, TaxonomyNode>();
		//		// Insert root node
		//		nodeMap.put(1, new TaxonomyNode(1, UniProtUtilities.TaxonomyRank.NO_RANK, "root"));

		// Iterate peptides and gather common taxonomy
//		for (PeptideHit peptideHit : peptideSet) {

			// Gather protein taxonomy nodes
//			List<TaxonomyNode> taxonNodes = new ArrayList<TaxonomyNode>();
//			LinkedList<ProteinHit> protTemp = new LinkedList<ProteinHit>();

//			for (ProteinHit proteinHit : peptideHit.getProteinHits()) {
////				taxonNodes.add(proteinHit.getTaxonomyNode());
//				protTemp.add(proteinHit);
//			}

//			TaxonomyUtils.determineTaxonomy(protTemp, definition, taxonomyMap, taxonomyNodeMap);
			
//			protTemp.clear();
			
			// Find common taxonomy node (either common ancestor or most specific depending on definition)
//			TaxonomyNode ancestor = taxonNodes.get(0);
//			for (int i = 0; i < taxonNodes.size(); i++) {
//				ancestor = definition.getCommonTaxonomyNode(ancestor, taxonNodes.get(i), taxonomyMap, taxonomyNodeMap);
//			}

			//			// Gets the parent node of the taxon node
			//			TaxonomyNode child = ancestor;
			//			TaxonomyNode parent = nodeMap.get(ancestor.getID());
			//			if (parent == null) {
			//				parent = child.getParentNode();
			//				// iterate up the taxonomy hierarchy until a mapped node is found (which may be the root)
			//				while (true) {
			//					// retrieve parent node from map
			//					TaxonomyNode temp = nodeMap.get(parent.getID());
			//
			//					if (temp == null) {
			//						// add child's parent node to map
			//						child.setParentNode(parent);
			//						nodeMap.put(parent.getID(), parent);
			//						child = parent;
			//						parent = parent.getParentNode();
			//					} else {
			//						// replace child's parent node with mapped parent and break out of loop
			//						child.setParentNode(temp);
			//						break;
			//					}
			//				}
			//			} else {
			//				ancestor = parent;
			//			}

			// set peptide hit taxon node to ancestor
//			peptideHit.setTaxonomyNode(ancestor);

			// possible TODO: determine spectrum taxonomy instead of inheriting directly from peptide
			// spectra dont have a taxonomy, This may cause problems down the road
			//			for (PeptideSpectrumMatch match : peptideHit.getPeptideSpectrumMatches()) {
			//				match.setTaxonomyNode(ancestor);
			//			}

			// fire progress notification


	//	/**
	//	 * Sets the taxonomy of meta-proteins contained in the specified list to the
	//	 * common taxonomy based on their child protein taxonomies.
	//	 * @param metaProteins the list of meta-proteins for which common protein
	//	 *  taxonomies shall be determined
	//	 * @param params the parameter map containing taxonomy definition rules
	//	 */
	//	public static void determineMetaProteinTaxonomy(ProteinHitList metaProteins, ParameterMap params) {
	//		TaxonomyUtils.determineTaxonomy(metaProteins, (TaxonomyUtils.TaxonomyDefinition) params.get("metaProteinTaxonomy").getValue());
	//	}

	/**
	 * Sets the taxonomy of proteins contained in the specified list to the
	 * common taxonomy based on their peptide taxonomies.
	 * @param proteins List of proteins hits.
	 * @param params the parameter map containing taxonomy definition rules
	 */
	public static void determineProteinTaxonomy(List<ProteinHit> proteins, TaxonomyUtils.TaxonomyDefinition definition, HashMap<Long, Taxonomy> taxonomyMap, HashMap<Long, TaxonomyNode> taxonomyNodeMap) {
		LinkedList<ProteinHit> protTemp = new LinkedList<ProteinHit>();
		for (ProteinHit proteinHit : proteins) {
			protTemp.clear();
			protTemp.add(proteinHit);
			TaxonomyUtils.determineTaxonomy(protTemp, definition, taxonomyMap, taxonomyNodeMap);
			TaxonomyNode taxNode = proteinHit.getTaxonomyNode();
			if (taxNode != null) {
				proteinHit.getUniProtEntry().setTaxonomyNode(taxNode);
			}
		}

	}

	/**
	 * Sets the taxonomy of the elements of the specified taxonomic list to the
	 * common taxonomy based on their child taxonomies.
	 *
	 * @param taxList the list of taxonomic instances
	 * @param definition the taxonomy definition
	 */
	public static void determineTaxonomy(List<? extends Taxonomic> taxList, TaxonomyUtils.TaxonomyDefinition definition, HashMap<Long, Taxonomy> taxonomyMap, HashMap<Long, TaxonomyNode> taxonomyNodeMap) {
		// iterate taxonomic list
		for (Taxonomic taxonomic : taxList) {

			// extract child taxonomy nodes
			List<TaxonomyNode> taxonNodes = new ArrayList<TaxonomyNode>();
			List<? extends Taxonomic> children = taxonomic.getTaxonomicChildren();
			for (Taxonomic childTax : children) {
				TaxonomyNode taxNode = childTax.getTaxonomyNode();
				if (taxNode == null) {
					System.err.println("ERROR: no taxonomic children found for " + childTax);
				}
				taxonNodes.add(taxNode);
			}

			// find common taxonomy node
			TaxonomyNode ancestor = taxonNodes.get(0);
			if (ancestor == null) {
				System.err.println("ERROR: no taxonomic ancestor found for " + taxonomic);
			}
			for (int i = 1; i < taxonNodes.size(); i++) {
				ancestor = definition.getCommonTaxonomyNode(ancestor, taxonNodes.get(i), taxonomyMap, taxonomyNodeMap);
			}

			// set common taxonomy node
			taxonomic.setTaxonomyNode(ancestor);

			// fire progress notification
			Client.getInstance().firePropertyChange("progressmade", false, true);
		}
	}

	/**
	 * Gets the tax name by the rank from the NCBI taxonomy.
	 * @param proteinHit Protein hit
	 * @param taxRank The taxonomic rank
	 * @return The name of the taxonomy.
	 */
	public static String getTaxonNameByRank(TaxonomyNode taxNode, UniProtUtilities.TaxonomyRank taxRank) {
		// Default value for taxonomy name.
		String taxName = "root";

		while (taxNode.getID() != 1) { // unequal to root
			if (taxNode.getRank() == taxRank) {
				taxName = taxNode.getName();
				return taxName;
			}
			taxNode = taxNode.getParentNode();
		}
		return taxName;
	}

	/**
	 * Gets the tax name by the rank from the NCBI taxonomy.
	 * @param proteinHit Protein hit
	 * @param taxRank The taxonomic rank
	 * @return The name of the taxonomy.
	 */
	public static TaxonomyNode getTaxonByRank(TaxonomyNode taxNode, UniProtUtilities.TaxonomyRank taxRank) {
		// Default value for taxonomy name.
		// Check for missing taxonomy
		if (taxNode.getID() == 1 && taxRank == UniProtUtilities.TaxonomyRank.ROOT) {
			return taxNode;
		}

		while (taxNode.getID() != 1) { // unequal to root
			if (taxNode.getRank() == taxRank) {
				return taxNode;
			}
			taxNode = taxNode.getParentNode();
		}
		return taxNode; 
	}

	/**
	 * Method to check whether a taxonomy belongs to a certain group determined by a certain NCBI taxonomy number.
	 * @param taxNode. Taxonomy node.
	 * @param filterTaxId. NCBI taxonomy ID.
	 * @return belongs to ? true / false
	 */
	public static boolean belongsToGroup(TaxonomyNode taxNode, long filterTaxId) {
		try {
			// switch to parents until we reach root
			// catch nullpointer exception (means there is a discontinuity in the tax tree)
			while (taxNode.getID() != taxNode.getParentNode().getID()) {
				if (taxNode.getID() == filterTaxId) {
					return true;
				}
				taxNode = taxNode.getParentNode();
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//		// To care for same taxID and especially for root as filtering level.
	//		if (filterTaxId == taxNode.getID()) { 
	//			belongsToGroup = true;
	//		} else {
	//			// Get all parents of the taxonNode and check whether they are equal to the filter level.
	//			while (taxNode.getParentNode() != null || (taxNode.getID() != 1)) {
	//				// Get parent taxon node of protein entry.
	//				try {
	//					taxNode = taxNode.getParentNode();
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//				// Check for filter ID
	//				if (filterTaxId == taxNode.getID()) {
	//					belongsToGroup = true;
	//					break;
	//				}
	//			}
	//		}
	//		return belongsToGroup;
	//	}

}	

