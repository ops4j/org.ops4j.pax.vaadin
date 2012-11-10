package com.vaadin.demo.sampler.features.upload;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;

@SuppressWarnings("serial")
public class UploadWithProgressMonitoring extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.OLD;
    }

    @Override
    public String getDescription() {
        return "Uploads can be monitored with several different listeners "
                + "and the upload data can be processed during the upload. "
                + "The upload does not block the entire UI so users can "
                + "navigate to other views in the application while the "
                + "upload is progressing. Other advanced upload features "
                + "used in this demo:<ul>"
                + "<li>Process the file during the upload</li>"
                + "<li>Track events that occure during the upload</li></ul>";
    }

    @Override
    public String getName() {
        return "Upload processing";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Upload.class),
                new APIResource(ProgressIndicator.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { UploadBasic.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
