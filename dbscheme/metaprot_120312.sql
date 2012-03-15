-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 12. März 2012 um 16:00
-- Server Version: 5.1.30
-- PHP-Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `metaprot`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `consensusspec`
--

CREATE TABLE IF NOT EXISTS `consensusspec` (
  `consensusspecid` int(11) NOT NULL AUTO_INCREMENT,
  `precursor_mz` decimal(12,4) NOT NULL,
  `precursor_int` decimal(12,4) NOT NULL,
  `precursor_charge` int(11) NOT NULL,
  `mzarray` text NOT NULL,
  `intarray` text NOT NULL,
  `chargearray` text NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`consensusspecid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `cruxhit`
--

CREATE TABLE IF NOT EXISTS `cruxhit` (
  `cruxhitid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  `scannumber` int(10) NOT NULL,
  `charge` int(3) NOT NULL,
  `precursor_mz` decimal(12,8) NOT NULL,
  `neutral_mass` decimal(12,8) NOT NULL,
  `peptide_mass` decimal(12,8) NOT NULL,
  `delta_cn` decimal(12,8) NOT NULL,
  `xcorr_score` decimal(12,8) NOT NULL,
  `xcorr_rank` int(10) NOT NULL,
  `percolator_score` decimal(12,8) NOT NULL,
  `percolator_rank` int(10) NOT NULL,
  `qvalue` decimal(12,8) NOT NULL,
  `matches_spectrum` int(10) NOT NULL,
  `cleavage_type` varchar(150) NOT NULL,
  `proteinid` text NOT NULL,
  `flank_aa` varchar(45) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` varchar(45) NOT NULL,
  PRIMARY KEY (`cruxhitid`),
  KEY `fk_cruxhit_peptide1` (`fk_peptideid`),
  KEY `fk_cruxhit_searchspectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11416 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `experiment`
--

CREATE TABLE IF NOT EXISTS `experiment` (
  `experimentid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_projectid` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`experimentid`),
  KEY `fk_experiment_project1` (`fk_projectid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `expproperty`
--

CREATE TABLE IF NOT EXISTS `expproperty` (
  `exppropertyid` int(11) NOT NULL,
  `fk_experimentid` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(45) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`exppropertyid`),
  KEY `fk_expproperty_experiment1` (`fk_experimentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `inspecthit`
--

CREATE TABLE IF NOT EXISTS `inspecthit` (
  `inspecthitid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  `scannumber` int(10) NOT NULL,
  `annotation` varchar(150) NOT NULL,
  `protein` text NOT NULL,
  `charge` int(3) NOT NULL,
  `mq_score` decimal(12,8) NOT NULL,
  `length` int(10) NOT NULL,
  `total_prm_score` decimal(12,8) NOT NULL,
  `median_prm_score` decimal(12,8) NOT NULL,
  `fraction_y` decimal(12,8) NOT NULL,
  `fraction_b` decimal(12,8) NOT NULL,
  `intensity` decimal(12,8) NOT NULL,
  `ntt` decimal(12,8) NOT NULL,
  `p_value` decimal(12,8) NOT NULL,
  `f_score` decimal(12,8) NOT NULL,
  `deltascore` decimal(12,8) NOT NULL,
  `deltascore_other` decimal(12,8) NOT NULL,
  `recordnumber` decimal(12,8) NOT NULL,
  `dbfilepos` int(11) NOT NULL,
  `specfilepos` int(11) NOT NULL,
  `precursor_mz` decimal(12,8) NOT NULL,
  `precursor_mz_error` decimal(12,8) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`inspecthitid`),
  KEY `fk_inspecthit_peptide1` (`fk_peptideid`),
  KEY `fk_inspecthit_searchspectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17010 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `libspectrum`
--

CREATE TABLE IF NOT EXISTS `libspectrum` (
  `libspectrumid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_experimentid` int(11) NOT NULL,
  `fk_consensusspecid` int(11) DEFAULT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`libspectrumid`),
  KEY `fk_spectrum_experiment1` (`fk_experimentid`),
  KEY `fk_spectrum_consensusspec1` (`fk_consensusspecid`),
  KEY `fk_libspectrum_spectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5006 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `omssahit`
--

CREATE TABLE IF NOT EXISTS `omssahit` (
  `omssahitid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  `hitsetnumber` int(10) NOT NULL,
  `evalue` decimal(12,8) NOT NULL,
  `pvalue` decimal(12,8) NOT NULL,
  `charge` int(2) NOT NULL,
  `mass` decimal(12,2) NOT NULL,
  `theomass` decimal(12,2) NOT NULL,
  `start` varchar(45) DEFAULT NULL,
  `end` varchar(45) DEFAULT NULL,
  `protein` varchar(45) NOT NULL,
  `qvalue` decimal(12,8) DEFAULT NULL,
  `pep` decimal(12,8) DEFAULT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` varchar(45) NOT NULL,
  PRIMARY KEY (`omssahitid`),
  KEY `fk_omssahit_peptide1` (`fk_peptideid`),
  KEY `fk_omssahit_searchspectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15817 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pep2prot`
--

CREATE TABLE IF NOT EXISTS `pep2prot` (
  `pep2protid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_peptideid` int(11) NOT NULL,
  `fk_proteinid` int(11) NOT NULL,
  PRIMARY KEY (`pep2protid`),
  KEY `fk_peptide2proteins_peptide1` (`fk_peptideid`),
  KEY `fk_peptide2proteins_proteins1` (`fk_proteinid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=340884 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `pepnovohit`
--

CREATE TABLE IF NOT EXISTS `pepnovohit` (
  `pepnovohitid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  `indexid` int(10) NOT NULL,
  `rankscore` decimal(12,4) NOT NULL,
  `pnvscore` decimal(12,4) NOT NULL,
  `n_gap` decimal(12,4) NOT NULL,
  `c_gap` decimal(12,4) NOT NULL,
  `precursor_mh` decimal(12,4) NOT NULL,
  `charge` int(3) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`pepnovohitid`),
  KEY `fk_pepnovohit_peptide1` (`fk_peptideid`),
  KEY `fk_pepnovohit_searchspectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `peptide`
--

CREATE TABLE IF NOT EXISTS `peptide` (
  `peptideid` int(11) NOT NULL AUTO_INCREMENT,
  `sequence` varchar(100) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`peptideid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=84029 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `projectid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`projectid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `property`
--

CREATE TABLE IF NOT EXISTS `property` (
  `propertyid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_projectid` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(45) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`propertyid`),
  KEY `fk_property_project1` (`fk_projectid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `protein`
--

CREATE TABLE IF NOT EXISTS `protein` (
  `proteinid` int(11) NOT NULL AUTO_INCREMENT,
  `accession` varchar(45) DEFAULT NULL,
  `description` text,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`proteinid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=28457 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `searchspectrum`
--

CREATE TABLE IF NOT EXISTS `searchspectrum` (
  `searchspectrumid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_experimentid` int(11) NOT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`searchspectrumid`),
  KEY `fk_spectrum_experiment1` (`fk_experimentid`),
  KEY `fk_searchspectrum_spectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=90388 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spec2pep`
--

CREATE TABLE IF NOT EXISTS `spec2pep` (
  `spec2pepid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  PRIMARY KEY (`spec2pepid`),
  KEY `fk_speclib_spectrum1` (`fk_spectrumid`),
  KEY `fk_speclibentry_peptide1` (`fk_peptideid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3237 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `specsearchhit`
--

CREATE TABLE IF NOT EXISTS `specsearchhit` (
  `specsearchhitid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fk_searchspectrumid` int(10) unsigned NOT NULL,
  `fk_libspectrumid` int(10) unsigned NOT NULL,
  `similarity` decimal(12,4) NOT NULL,
  `creationdate` varchar(45) NOT NULL,
  `modificationdate` varchar(45) NOT NULL,
  PRIMARY KEY (`specsearchhitid`),
  KEY `fk_specsearchhit_searchspectrum1` (`fk_searchspectrumid`),
  KEY `fk_specsearchhit_libspectrum1` (`fk_libspectrumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spectrum`
--

CREATE TABLE IF NOT EXISTS `spectrum` (
  `spectrumid` int(10) unsigned NOT NULL,
  `title` varchar(255) NOT NULL,
  `precursor_mz` decimal(12,4) NOT NULL,
  `precursor_int` decimal(12,4) NOT NULL,
  `precursor_charge` int(3) NOT NULL,
  `mzarray` text NOT NULL,
  `intarray` text NOT NULL,
  `chargearray` text NOT NULL,
  `total_int` decimal(20,4) NOT NULL,
  `maximum_int` varchar(45) NOT NULL,
  `creationtime` datetime NOT NULL,
  `modificationtime` datetime NOT NULL,
  PRIMARY KEY (`spectrumid`),
  KEY `fk_spectrumfile_libspectrum1` (`spectrumid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `xtandemhit`
--

CREATE TABLE IF NOT EXISTS `xtandemhit` (
  `xtandemhitid` int(11) NOT NULL AUTO_INCREMENT,
  `fk_spectrumid` int(10) unsigned NOT NULL,
  `fk_peptideid` int(11) NOT NULL,
  `domainid` varchar(45) NOT NULL,
  `protein` varchar(45) NOT NULL,
  `start` int(10) unsigned NOT NULL,
  `end` int(10) unsigned NOT NULL,
  `evalue` decimal(12,8) NOT NULL,
  `precursor` decimal(12,8) NOT NULL,
  `delta` decimal(12,8) NOT NULL,
  `hyperscore` decimal(12,8) NOT NULL,
  `pre` varchar(45) NOT NULL,
  `post` varchar(45) NOT NULL,
  `misscleavages` int(10) NOT NULL,
  `qvalue` decimal(12,8) DEFAULT NULL,
  `pep` decimal(12,8) DEFAULT NULL,
  `creationdate` datetime NOT NULL,
  `modificationdate` datetime NOT NULL,
  PRIMARY KEY (`xtandemhitid`),
  KEY `fk_xtandemhit_peptide1` (`fk_peptideid`),
  KEY `fk_xtandemhit_searchspectrum1` (`fk_spectrumid`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=17241 ;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `cruxhit`
--
ALTER TABLE `cruxhit`
  ADD CONSTRAINT `fk_cruxhit_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_cruxhit_searchspectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `experiment`
--
ALTER TABLE `experiment`
  ADD CONSTRAINT `fk_experiment_project1` FOREIGN KEY (`fk_projectid`) REFERENCES `project` (`projectid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `expproperty`
--
ALTER TABLE `expproperty`
  ADD CONSTRAINT `fk_expproperty_experiment1` FOREIGN KEY (`fk_experimentid`) REFERENCES `experiment` (`experimentid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `inspecthit`
--
ALTER TABLE `inspecthit`
  ADD CONSTRAINT `fk_inspecthit_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_inspecthit_searchspectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `libspectrum`
--
ALTER TABLE `libspectrum`
  ADD CONSTRAINT `fk_spectrum_consensusspec1` FOREIGN KEY (`fk_consensusspecid`) REFERENCES `consensusspec` (`consensusspecid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_spectrum_experiment1` FOREIGN KEY (`fk_experimentid`) REFERENCES `experiment` (`experimentid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_libspectrum_spectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `spectrum` (`spectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `omssahit`
--
ALTER TABLE `omssahit`
  ADD CONSTRAINT `fk_omssahit_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_omssahit_searchspectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `pep2prot`
--
ALTER TABLE `pep2prot`
  ADD CONSTRAINT `fk_peptide2proteins_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_peptide2proteins_proteins1` FOREIGN KEY (`fk_proteinid`) REFERENCES `protein` (`proteinid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `pepnovohit`
--
ALTER TABLE `pepnovohit`
  ADD CONSTRAINT `fk_pepnovohit_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_pepnovohit_searchspectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `fk_property_project1` FOREIGN KEY (`fk_projectid`) REFERENCES `project` (`projectid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `searchspectrum`
--
ALTER TABLE `searchspectrum`
  ADD CONSTRAINT `fk_spectrum_experiment10` FOREIGN KEY (`fk_experimentid`) REFERENCES `experiment` (`experimentid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_searchspectrum_spectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `spectrum` (`spectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `spec2pep`
--
ALTER TABLE `spec2pep`
  ADD CONSTRAINT `fk_speclibentry_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_speclib_spectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `libspectrum` (`libspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `specsearchhit`
--
ALTER TABLE `specsearchhit`
  ADD CONSTRAINT `fk_specsearchhit_searchspectrum1` FOREIGN KEY (`fk_searchspectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_specsearchhit_libspectrum1` FOREIGN KEY (`fk_libspectrumid`) REFERENCES `libspectrum` (`libspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints der Tabelle `xtandemhit`
--
ALTER TABLE `xtandemhit`
  ADD CONSTRAINT `fk_xtandemhit_peptide1` FOREIGN KEY (`fk_peptideid`) REFERENCES `peptide` (`peptideid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_xtandemhit_searchspectrum1` FOREIGN KEY (`fk_spectrumid`) REFERENCES `searchspectrum` (`searchspectrumid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;