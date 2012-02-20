package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AnomalyClassificatorFactory {
    HashMap<String, Class<? extends AnomalyClassificator>> nameToClMapping = new LinkedHashMap<String, Class<? extends AnomalyClassificator>>();
    HashMap<Class<? extends AnomalyClassificator>, Class<? extends AnomalyConfigurator>> clToCfgMapping = new LinkedHashMap<Class<? extends AnomalyClassificator>, Class<? extends AnomalyConfigurator>>();
    HashMap<Class<? extends AnomalyConfigurator>, Class<? extends AnomalyConfiguratorPanel>> cfToPanelMapping = new LinkedHashMap<Class<? extends AnomalyConfigurator>, Class<? extends AnomalyConfiguratorPanel>>();

    public void add(String name, Class<? extends AnomalyClassificator> adClass, Class<? extends AnomalyConfigurator> cfgClass, Class<? extends AnomalyConfiguratorPanel> cfgPanelClass) {

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

    public AnomalyClassificatorFactory() {
    }

    public AnomalyClassificator create(String acName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (nameToClMapping.containsKey(acName)) {
            return nameToClMapping.get(acName).newInstance();
        }
        throw new ClassNotFoundException("missing classificator with name: " + acName);
    }

    public AnomalyConfigurator getConfigurator(AnomalyClassificator ac) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
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

    public String[] getRegisteredACNames() {
        return (nameToClMapping.keySet().toArray(new String[nameToClMapping.keySet().size()]));
    }
}
