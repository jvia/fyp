package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

public class AnomalyClassifierFactory {
	private final HashMap<String, Class<? extends AnomalyClassifier>> nameToClMapping = new LinkedHashMap<String, Class<? extends AnomalyClassifier>>();
	private final HashMap<Class<? extends AnomalyClassifier>, Class<? extends AnomalyConfigurator>> clToCfgMapping = new LinkedHashMap<Class<? extends AnomalyClassifier>, Class<? extends AnomalyConfigurator>>();
	private final HashMap<Class<? extends AnomalyConfigurator>, Class<? extends AnomalyConfiguratorPanel>> cfToPanelMapping = new LinkedHashMap<Class<? extends AnomalyConfigurator>, Class<? extends AnomalyConfiguratorPanel>>();

	public void add(String name, Class<? extends AnomalyClassifier> adClass, Class<? extends AnomalyConfigurator> cfgClass, Class<? extends AnomalyConfiguratorPanel> cfgPanelClass) {

		if (name != null && adClass != null) {
			nameToClMapping.put(name, adClass);
		}

		if (adClass != null && cfgClass != null) {
			clToCfgMapping.put(adClass, cfgClass);
		}
		if (cfgClass != null && cfgPanelClass != null) {
			cfToPanelMapping.put(cfgClass, cfgPanelClass);
		}
	}

	public AnomalyClassifierFactory() {
	}

	public AnomalyClassifier create(String acName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (nameToClMapping.containsKey(acName)) {
			return nameToClMapping.get(acName).newInstance();
		}
		throw new ClassNotFoundException("missing classificator with name: " + acName);
	}

	public AnomalyConfigurator getConfigurator(AnomalyClassifier ac) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (clToCfgMapping.containsKey(ac.getClass())) {
			return clToCfgMapping.get(ac.getClass()).newInstance();
		}
		throw new ClassNotFoundException("missing configurator for" + ac.getClass().getCanonicalName());
	}

	public JPanel getConfiguratorPanel(AnomalyConfigurator aCfg) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		if (cfToPanelMapping.containsKey(aCfg.getClass())) {
			AnomalyConfiguratorPanel p = cfToPanelMapping.get(aCfg.getClass()).newInstance();
			p.setAnomalyConfigurator(aCfg);
			return p;
		}
		throw new ClassNotFoundException("panel for " + aCfg.getClass().getCanonicalName() + " not found");
	}
	public String[] getRegisteredACNames(){
		return (nameToClMapping.keySet().toArray(new String[nameToClMapping.keySet().size()]));
	}
}
