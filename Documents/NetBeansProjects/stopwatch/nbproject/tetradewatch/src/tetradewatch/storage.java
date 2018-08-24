/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetradewatch;

import java.io.File;
import java.io.IOException;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.awt.FileDialog;
import java.awt.Frame;

/**
 *
 * @author Nathan Home PC
 */
public class storage {
   
    String clientRegion = "*** Client region ***";
    String bucketName = "*** Bucket name ***";
    String stringObjKeyName = "*** String object key name ***";
    String fileObjKeyName = "*** File object key name ***";
    String fileName = "*** Path to file to upload ***"; 
    AmazonS3 s3Client = null;

    //authenticate the cloud storage
    public boolean method_1()
    {
        boolean valid = false;
        
        try 
        {
            s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();            
            valid = true;
        }
        catch(AmazonServiceException e) 
        {
            e.printStackTrace();
        }
        
        return valid;
       
    }
    
    //select a local file / or even an online link
    public void method_2()
    {
        FileDialog dialog = new FileDialog((Frame)null, "Select File to Open");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        fileName = dialog.getFile();
    }
    
    //upload the file to the cloud storage
    public void method_3( )
    {
        try
        {
            if(method_1())
            {
                method_2();
                        
                PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentType("plain/text");
                metadata.addUserMetadata("x-amz-meta-title", "someTitle");
                request.setMetadata(metadata);
                s3Client.putObject(request);
            }
        }
        catch(AmazonServiceException e) 
        {
            e.printStackTrace();
        }
    }
    
}
