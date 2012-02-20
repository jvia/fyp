package org.bham.aucom.main;


public class CreateClassIdsFromData {

    public static void main(String[] args) {
//		try {
//			Parameter[] p = new Parameter[3];
//			p[0] = new FlaggedOption("existingFile", JSAP.STRING_PARSER, "", JSAP.NOT_REQUIRED, 'f',JSAP.NO_LONGFLAG, "an existing file with class ids");
//			p[1] = new FlaggedOption("saveFile", JSAP.STRING_PARSER, "", JSAP.REQUIRED, 's',JSAP.NO_LONGFLAG, "File to sace the class ids");
//			p[2] = new FlaggedOption("File|Folder", JSAP.STRING_PARSER, "", JSAP.REQUIRED, 'i',JSAP.NO_LONGFLAG, "Folder with .dat files or a .dat file which has to be scanned for class ids");
//			SimpleJSAP o = new SimpleJSAP("class id generator","generates file with classids from a file or a set of files in a folder", p);
//			JSAPResult config = o.parse(args);
//	        if ( o.messagePrinted() ) System.exit( 1 );
//	        System.out.println(o.messagePrinted());
//	        String saveFileName = config.getString("saveFile");
//	        String fileOrFolder = config.getString("dat");
//	        File f = new File(fileOrFolder);
//	        List<String> files = new ArrayList<String>();
//	        if(f.isDirectory()){
//	        	files.addAll(Arrays.asList(f.list(new DatFileFilter())));
//	        }
//	        ScoureScopeTypeEncoder classifier = ScoureScopeTypeEncoder.getInstance();
//	        List<String> strClasses = new ArrayList<String>();
//	        try {
//	        	Sink filesink = new OutputStreamSink(new FileOutputStream(new File(saveFileName)));
//	        	Node classifierSink = new ClassifierSink(classifier);
//
//	        	BlockingQueue<Observation> queue = new LinkedBlockingQueue<Observation>();
//	        	Graph g = new Graph();
//	        	Node src = new  CollectionIteratorSource<String>(files);
//	        	Node classSrc = new  CollectionIteratorSource<String>(strClasses);
//	        	Sink collectionSink = new CollectionSink2(queue);
//				Node src2 = new QueueHeadSource<SingleData>(queue);
//				Node extract = new ExtreactFileToData();
//				Node filenameToFile = new Filename2File();
//				
//				g.connect(src, filenameToFile);
//				g.connect(filenameToFile, extract);
//				g.connect(extract, collectionSink);
//				
//				g.connect(src2, classifierSink);
//				g.connect(classSrc, filesink);
//				DotGraphExport exp = new DotGraphExport();
//				exp.addGraph("test", g);
//				try {
//					FileWriter ff = new FileWriter(new File(
//							"createclassidsgraph.dot"));
//					ff.write(exp.getDotString());
//					ff.flush();
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//				EngineThread runner = new EngineThread(g);
//				runner.start();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//	        
//		} catch (JSAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
}
