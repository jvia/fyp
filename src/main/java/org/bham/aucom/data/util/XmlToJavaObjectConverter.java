package org.bham.aucom.data.util;

import nu.xom.Element;
import nu.xom.XPathContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class XmlToJavaObjectConverter {

    private XPathContext context = new XPathContext("ts", "http://www.cor-lab.de/formats/ts/1.0");

    public Object getObjectFrom(Element element) throws SecurityException,
                                                        IllegalArgumentException,
                                                        NoSuchMethodException,
                                                        InstantiationException,
                                                        IllegalAccessException,
                                                        InvocationTargetException {
        if (isPrimitiveParameter(element))
            return getPrimitiveObjectFromElement(element);
        return getComplexObjectFrom(element);
    }

    protected Class<?>[] getParameterClassesFrom(Element xmlElement) {
        Class<?>[] oneParameterSet = new Class<?>[xmlElement.getChildCount()];
        for (int i = 0; i < xmlElement.getChildCount(); i++) {
            oneParameterSet[i] = getClassFrom((Element) xmlElement.getChild(i));
        }
        return oneParameterSet;
    }
    /*
     * Creates an object of a complex type 
     */

    protected Object getComplexObjectFrom(Element element) throws SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException,
                                                                  InvocationTargetException {
        Class<?> complexClass = getClassFrom(element);
        List<Element> parameterElements = getParameterXmlLements(element);

        /*
         * Special case where the object to generate has no parameters 
         */
        if (parameterElements.size() == 0) {
            Constructor<?> constructor = complexClass.getConstructor();
            return constructor.newInstance();
        }
        Class<?>[] parameterClasses = getParameterClassesFrom(element);
        Object[] parameterValues = new Object[parameterElements.size()];
        for (int i = 0; i < parameterElements.size(); i++) {
            Element parameterXmlElement = parameterElements.get(i);
            if (isPrimitiveParameter(parameterXmlElement)) {
                parameterValues[i] = getPrimitiveObjectFromElement(parameterXmlElement);
            } else {
                parameterValues[i] = getComplexObjectFrom(parameterXmlElement);
            }
        }
        Constructor<?> constructor = complexClass.getConstructor(parameterClasses);
        return constructor.newInstance(parameterValues);
    }
    /*
     * Creates an object of a primitive type wrapped in the appropriate java wrapper (i.e., Double, Float, Integer, ...)
     */

    protected Object getPrimitiveObjectFromElement(Element result) {
        try {
            Class<?> objectClass = getClassFrom(result);
            String value = result.getValue();
            Class<?>[] objectClasses = {String.class};
            Constructor<?> paramConstructor = objectClass.getConstructor(objectClasses);
            return paramConstructor.newInstance(value);
        } catch (SecurityException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (InstantiationException exception) {
            exception.printStackTrace();
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected Class<?> getClassFrom(Element result) {
        try {
            return Class.forName(result.getAttributeValue("class"));
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    //	protected Object[] getParameterValuesFromElement(Element xmlElement) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
//		Nodes parameters = xmlElement.query("./ts:parameter", this.context);
//		Object[] parameterValues = new Object[parameters.size()];
//		for (int i = 0; i < parameters.size(); i++) {
//			if(isPrimitiveParameter(xmlElement))
//				parameterValues[i] = getPrimitiveObjectFromElement((Element) parameters.get(i));
//			else
//				parameterValues[i] = getComplexObjectFrom((Element) parameters.get(i));
//		}
//		return parameterValues;
//	}
    public boolean isPrimitiveParameter(Element elementToTest) {
        return elementToTest.getQualifiedName().equals("ts:primitive");
    }

    private List<Element> getParameterXmlLements(Element element) {
        List<Element> elements = new ArrayList<Element>();
        for (int i = 0; i < element.getChildCount(); i++) {
            elements.add((Element) element.getChild(0));
        }
        return elements;
    }

    /**
     * @return the context
     */
    public XPathContext getContext() {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext(XPathContext context) {
        this.context = context;
    }
}
