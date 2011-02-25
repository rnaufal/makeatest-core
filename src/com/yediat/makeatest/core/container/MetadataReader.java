package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Responsável pela leitura dos metadados (anotações) dos métodos e também pela
 * criação do container das anotações
 * 
 * @author deborachama
 * 
 */
public class MetadataReader {

	final Logger logger = LoggerFactory.getLogger(MetadataReader.class);

	private MetadataContainer container = null;

	public MetadataReader(Class<?> klass) throws MakeATestInitializationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Parameter klass is " + klass.getName());
		}
		this.container = new MetadataContainer();
		this.stetament(klass);
	}

	public MetadataContainer getContainer() {
		return this.container;
	}

	private void stetament(Class<?> klass) throws MakeATestInitializationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Parameter klass is " + klass.getName());
		}
		stetamentClass(klass);
		stetamentFields(klass);
		stetamentMethods(klass);
	}

	private void stetamentClass(Class<?> klass) throws MakeATestInitializationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Parameter klass is " + klass.getName());
		}
		Annotation[] annotations = filterMakeATestAnnotation(klass.getAnnotations());
		if (annotations.length > 0) {
			this.processAnnotations(annotations, klass);
		}
	}

	private void stetamentFields(Class<?> klass) throws MakeATestInitializationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Parameter klass is " + klass.getName());
		}
		Field[] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = filterMakeATestAnnotation(field.getAnnotations());
			if (annotations.length > 0) {
				this.processAnnotations(annotations, field);
			}
		}
	}

	private void stetamentMethods(Class<?> klass) throws MakeATestInitializationException {
		if (logger.isDebugEnabled()) {
			logger.debug("Parameter klass is " + klass.getName());
		}
		Method[] methods = klass.getMethods();
		for (Method method : methods) {
			Annotation[] annotations = filterMakeATestAnnotation(method.getAnnotations());
			if (annotations.length > 0) {
				this.processAnnotations(annotations, method);
			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void processAnnotations(Annotation[] annotations, Object key) throws MakeATestInitializationException {
		/**
		 * Logger debugger
		 */
		StringBuffer log = new StringBuffer("Reade the annotaions: \n");
		for (Annotation annotation : annotations) {
			log.append(annotation.toString() + " \n");
		}
		if (logger.isDebugEnabled()) {
			logger.debug(log + "key: " + key);
		}

		for (Annotation annotation : annotations) {
			MakeATestReader reader = (MakeATestReader) annotation.annotationType().getAnnotation(MakeATestReader.class);
			MakeATestProxyBehavior[] proxyBehavior = reader.proxyBehavior();
			MakeATestScope scope = reader.scope();

			AnnotationProperties annotationProperties = new AnnotationProperties();

			Class<? extends MakeATestReaderInterface> readerClass = reader.reader();
			try {
				MakeATestReaderInterface annotationReader = readerClass.newInstance();

				annotationProperties.setProxyBehabior(proxyBehavior);
				annotationProperties.setAnnotated(key);
				annotationProperties.setAnnotation(annotation);

				annotationReader.readAnnotation(annotation, annotationProperties);
				this.container.put(scope, key, annotationProperties);
			} catch (ClassCastException ce) {
				String type = reader.reader().getGenericInterfaces()[0].toString();
				Matcher m = Pattern.compile(".*<.*\\.(.*)>.*").matcher(type);
				m.matches();
				MakeATestInitializationException makeATestException = new MakeATestInitializationException(
						"Class Cast Exception in call method readAnnotation(...) in reader class: \n Cast Error: \"(" + m.group(1) + ") " + annotation.annotationType().getSimpleName()
								+ "\" \n Reader Class: " + readerClass.getName(), ce);
				makeATestException.setStackTrace(ce.getStackTrace());
				throw makeATestException;
			} catch (Exception e) {
				MakeATestInitializationException makeATestException = new MakeATestInitializationException("Cannot initialize reader: " + readerClass.getName(), e);
				makeATestException.setStackTrace(e.getStackTrace());
				throw makeATestException;
			}
		}
	}

	private Annotation[] filterMakeATestAnnotation(Annotation[] annotations) {
		List<Annotation> annotationsMakeATest = new ArrayList<Annotation>();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType().isAnnotationPresent(MakeATestReader.class)) {
				annotationsMakeATest.add(annotation);
			}
		}
		Annotation[] returnAnnotations = new Annotation[annotationsMakeATest.size()];
		return (Annotation[]) annotationsMakeATest.toArray(returnAnnotations);
	}

}
