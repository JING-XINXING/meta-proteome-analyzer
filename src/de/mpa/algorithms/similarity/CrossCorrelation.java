package de.mpa.algorithms.similarity;

import java.util.HashMap;
import java.util.Map;


public class CrossCorrelation implements SpectrumComparator {
	
	/**
	 * The input vectorization method.
	 */
	private final Vectorization vect;
	
	/**
	 * The input transformation method.
	 */
	private final Transformation trafo;

	/**
	 * The amount of neighboring bins that are evaluated (in both positive and negative m/z direction) during correlation.
	 */
	private final int offsets;
	
	/**
	 * The peak map of the source spectrum which gets auto-correlated during preparation.
	 */
	private Map<Double,Double> peaksSrc;
	
	/**
	 * The bin width.
	 */
	private final double binWidth;
	
	/**
	 * The auto-correlation score of the source spectrum. Used for normalization purposes.
	 */
	private double autoCorr;
	
	
	/**
	 * The similarity score between source spectrum and target spectrum.
	 * Ranges between 0.0 (negative scores are cut off) and around 1.0
	 * (higher scores are possible due to poor auto-correlation of the source spectrum).
	 */
	private double similarity;
	
	public CrossCorrelation(Vectorization vect, Transformation trafo, double binWidth, int offsets) {
		this.vect = vect;
		this.trafo = trafo;
		this.binWidth = binWidth;
		this.offsets = offsets;
	}

	@Override
	public void prepare(Map<Double, Double> inputPeaksSrc) {
		
		// TODO: make adjustments for peak matching method, if possible
		
		// vectorize input source spectrum
		Map<Double, Double> vectPeaksSrc = this.vect.vectorize(inputPeaksSrc, this.trafo);

        this.peaksSrc = new HashMap<Double, Double>(vectPeaksSrc.size());
		
		// determine source spectrum magnitude
		double magSrc = 0.0;
		for (double intenSrc : vectPeaksSrc.values())
			magSrc += intenSrc * intenSrc;
		magSrc = Math.sqrt(magSrc);
		
		// apply cross-correlation transformation
		for (Map.Entry<Double, Double> peakSrc : vectPeaksSrc.entrySet()) {
			// normalize intensity
			double intenSrc = peakSrc.getValue() / magSrc;
			// apply cross-correlation processing and store resulting peaks
			for (int tau = -offsets; tau <= offsets; tau++) {
				if (tau == 0) {
					peaksSrc.put(peakSrc.getKey(), intenSrc);
				} else {
					double offsetMz = peakSrc.getKey() + tau*binWidth;
					Double existingInten = peaksSrc.get(offsetMz);
					if (existingInten == null) existingInten = 0.0;
					peaksSrc.put(offsetMz, existingInten - intenSrc/(2*offsets));
				}
			}
		}

		// determine auto-correlation (squared magnitude, essentially)
		autoCorr = 0.0;
		for (double intenSrc : peaksSrc.values()) {
			autoCorr += intenSrc * intenSrc;
		}
	}

	@Override
	public void compareTo(Map<Double, Double> inputPeaksTrg) {

		Map<Double, Double> peaksTrg = vect.vectorize(inputPeaksTrg, trafo);

		// determine target spectrum magnitude
		double magTrg = 0.0;
		for (double intenTrg : peaksTrg.values())
			magTrg += intenTrg * intenTrg;
		magTrg = Math.sqrt(magTrg);

		// calculate dot product
		double numer = 0.0;
		for (Map.Entry<Double, Double> peakTrg : peaksTrg.entrySet()) {
			Double intenSrc = this.peaksSrc.get(peakTrg.getKey());
			if (intenSrc != null)
				numer += intenSrc * (peakTrg.getValue() / magTrg);
		}
		
		// normalize score using auto-correlation
		double similarity = numer / this.autoCorr;
		this.similarity = (similarity > 0.0) ? similarity : 0.0;	// cut off negative scores
	}

	@Override
	public double getSimilarity() {
		return this.similarity;
	}

	@Override
	public Map<Double, Double> getSourcePeaks() {
		return this.peaksSrc;
	}

	@Override
	public Vectorization getVectorization() {
		return this.vect;
	}

}
