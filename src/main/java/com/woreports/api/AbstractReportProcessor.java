package com.woreports.api;

import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.eocontrol.EOSortOrdering;
import com.webobjects.foundation.NSArray;

/**
 * @author <a href="mailto:hprange@gmail.com">Henrique Prange</a>
 */
public abstract class AbstractReportProcessor implements ReportProcessor {
    public AbstractReportProcessor() {
    }

    protected abstract byte[] handleProcessing(Format format, ReportModel model, Map<String, Object> parameters, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) throws ReportProcessingException;

    protected abstract byte[] handleProcessing(Format format, ReportModel model, Map<String, Object> parameters, JRDataSource dataSource) throws ReportProcessingException;

    @Override
    public final byte[] process(Format format, ReportModel model, Map<String, Object> parameters) throws ReportProcessingException {
        return process(format, model, parameters, (EOQualifier) null);
    }

    @Override
    public final byte[] process(Format format, ReportModel model, Map<String, Object> parameters, EOQualifier qualifier) throws ReportProcessingException {
        return process(format, model, parameters, qualifier, NSArray.<EOSortOrdering> emptyArray());
    }

    @Override
    public final byte[] process(Format format, ReportModel model, Map<String, Object> parameters, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) throws ReportProcessingException {
        return handleProcessing(format, model, parameters, qualifier, sortOrderings);
    }

    @Override
    public final byte[] process(Format format, ReportModel model, Map<String, Object> parameters, JRDataSource dataSource) throws ReportProcessingException {
        return handleProcessing(format, model, parameters, dataSource);
    }
}
