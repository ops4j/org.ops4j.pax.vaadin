package com.vaadin.demo.sampler.features.upload;

import com.vaadin.demo.sampler.APIResource;
import com.vaadin.demo.sampler.Feature;
import com.vaadin.demo.sampler.NamedExternalResource;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;

@SuppressWarnings("serial")
public class ImmediateUpload extends Feature {

    @Override
    public Version getSinceVersion() {
        return Version.V62;
    }

    @Override
    public String getDescription() {
        return "The upload component can be configured to work as a single-click upload, that starts right after the user has selected the file to upload.<br /><br />In this sample the upload is deliberately slow, so that even small files show the progress indicator.";
    }

    @Override
    public String getName() {
        return "Single-click upload";
    }

    @Override
    public APIResource[] getRelatedAPI() {
        return new APIResource[] { new APIResource(Upload.class),
                new APIResource(ProgressIndicator.class) };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Feature>[] getRelatedFeatures() {
        return new Class[] { UploadBasic.class,
                UploadWithProgressMonitoring.class };
    }

    @Override
    public NamedExternalResource[] getRelatedResources() {
        // TODO Auto-generated method stub
        return null;
    }

}
