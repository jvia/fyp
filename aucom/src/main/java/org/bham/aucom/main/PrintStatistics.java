package org.bham.aucom.main;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.fts.graph.DataSequenceStatistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

public class PrintStatistics {
    /*
     * @param sequence1
     * @param statisticsSequence1
     */
    private void printDataSequenceStatistic(TimeSeries<DataType> sequence1) {
        DataSequenceStatistics statisticsSequence1 = new DataSequenceStatistics(sequence1);
        DecimalFormat decimalNumberFromatter = new DecimalFormat("00");
        DecimalFormat decimalNumberFromatter2 = new DecimalFormat("00.000");

        System.out.println(sequence1 + " --------------------");
        System.out.println("number of events: " + statisticsSequence1.getNumberElements());
        System.out.println("duration: " + statisticsSequence1.getDurationAsString());
        System.out.println("number classes " + statisticsSequence1.getNumberClasses());
        System.out.println("*Sources: " + decimalNumberFromatter.format(statisticsSequence1.getSources().size()) + "                                       *");
        for (String source : statisticsSequence1.getSources()) {
            System.out.println(source + "(" + statisticsSequence1.getCountForSources(source) + "; " + decimalNumberFromatter2.format(statisticsSequence1.getCountFrequencyForSource(source)) + "Hz) ");
        }
        System.out.println("");
        System.out.println("*Scopes: " + decimalNumberFromatter.format(statisticsSequence1.getScopes().size()) + "                                       *");
        for (String scope : statisticsSequence1.getScopes()) {
            System.out.println(scope + " ");
        }
        System.out.println("");
        System.out.println("*Types: " + decimalNumberFromatter.format(statisticsSequence1.getTypes().size()) + "                                       *");
        for (String type : statisticsSequence1.getTypes()) {
            System.out.println(type + " ");
        }
        System.out.println("");
        System.out.println("-------------------- " + sequence1 + " --------------------");
        System.out.println("");
    }

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        PrintStatistics statisticsPrinter = new PrintStatistics();
        for (String arg : args) {
            try {
                TimeSeries<DataType> ts = (TimeSeries<DataType>) AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking(arg);
                DataManager.getInstance().addTimeSeries(ts);
                statisticsPrinter.printDataSequenceStatistic(ts);
            } catch (FileNotFoundException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            } catch (ValidityException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            } catch (DataAlreadyExistsException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            } catch (ParsingException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            } catch (IOException exception) {
                // TODO Auto-generated catch block
                exception.printStackTrace();
            }
        }
    }
}