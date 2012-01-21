package org.bham.aucom.data.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

public abstract class BinaryIO<T>  implements IOInterface<T> {
	Class<T> cl;
	public BinaryIO(Class<T> cl) {
		this.cl = cl ;
	}
	@Override
	public T read(File fileToLoadFrom) throws FileNotFoundException, ValidityException, ParsingException, IOException {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileToLoadFrom));
			Object faultDetectionModel;
			try {
				faultDetectionModel = objectInputStream.readObject();
				objectInputStream.close();
				return this.cl.cast(faultDetectionModel);
			} catch (ClassNotFoundException exception) {
				throw new ParsingException("tried to parse an unknown class from " + fileToLoadFrom.getAbsolutePath());
			}
	}

	@Override
	public boolean write(Object faultDetectionModelTowrite, File file) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(faultDetectionModelTowrite);
			objectOutputStream.flush();
			objectOutputStream.close();
			return true;
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return false;
	}

}