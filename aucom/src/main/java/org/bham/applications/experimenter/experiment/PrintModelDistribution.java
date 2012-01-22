package org.bham.applications.experimenter.experiment;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.applications.experimenter.data.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class PrintModelDistribution implements Experiment {

    private String ml;
    private String wd;

    public PrintModelDistribution(String wd, String ml) {
        this.wd = wd;
        this.ml = ml;
    }

    @Override
    public void preprocess() throws Exception {
    }

    @Override
    public void process() throws Exception {
        T2GramModelI model = loadModelFile(ml);

        ProbabilityDistribution pd = model.getDistributionFor(0, 1);
        for (double d = 0.0; d <= pd.getMaxProbability(); d += 1.0)
            System.out.printf("%.2f    %.2f", d, pd.getProbability(d));
    }

    @Override
    public void postprocess() throws Exception {
    }

    @Override
    public Result call() throws Exception {
        process();
        return null;
    }

    private T2GramModelI loadModelFile(String ml) {
        T2GramModelI _model = null;
        try {
            _model = (T2GramModelI) AucomIO.getInstance().readFaultDetectionModel(new File(wd + "/" + ml));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataAlreadyExistsException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidityException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _model;
    }
}
