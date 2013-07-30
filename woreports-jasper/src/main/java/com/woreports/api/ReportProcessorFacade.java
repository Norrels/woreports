package com.woreports.api;

import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public class ReportProcessorFacade implements ReportProcessor {
    private final Set<ReportProcessor> processors;

    @Inject
    public ReportProcessorFacade(@Named("ForFacade") final Set<ReportProcessor> processors) {
        this.processors = processors;
    }

    @Override
    public byte[] process(Format format, ReportModel model, Map<String, Object> parameters) throws ReportProcessingException {
        return process(format, model, parameters, (EOQualifier) null);
    }

    @Override
    public byte[] process(Format format, ReportModel model, Map<String, Object> parameters, EOQualifier qualifier) throws ReportProcessingException {
        return process(format, model, parameters, qualifier, NSArray.<EOSortOrdering> emptyArray());
    }

    @Override
    public byte[] process(Format format, ReportModel model, Map<String, Object> parameters, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) throws ReportProcessingException {
        byte[] result = null;

        for (ReportProcessor processor : processors) {
            result = processor.process(format, model, parameters, qualifier, sortOrderings);

            if (result != null) {
                break;
            }
        }

        return result;
    }

    @Override
    public byte[] process(Format format, ReportModel model, Map<String, Object> parameters, JRDataSource dataSource) throws ReportProcessingException {
        byte[] result = null;

        for (ReportProcessor processor : processors) {
            result = processor.process(format, model, parameters, dataSource);

            if (result != null) {
                break;
            }
        }

        return result;
    }
}