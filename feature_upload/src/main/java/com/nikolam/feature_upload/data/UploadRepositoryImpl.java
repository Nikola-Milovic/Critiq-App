package com.nikolam.feature_upload.data;

import android.content.Context;
import android.net.Uri;

import com.nikolam.common.domain.executor.ThreadExecutor;
import com.nikolam.feature_upload.data.models.UploadResponse;
import com.nikolam.feature_upload.domain.UploadRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ViewModelScoped;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import timber.log.Timber;

@ViewModelScoped
public class UploadRepositoryImpl implements UploadRepository {

    private final UploadService service;
    private final ThreadExecutor threadExecutor;
    private final Context context;

    @Inject
    public UploadRepositoryImpl(UploadService service, ThreadExecutor executor, @ApplicationContext Context context) {
        this.service = service;
        this.threadExecutor = executor;
        this.context = context;
    }


    @Override
    public Observable<UploadResponse> uploadCritiqImage(Uri fileUri) {
        InputStream in = null;
        try {
            in = context.getContentResolver().openInputStream(fileUri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OutputStream out = null;
        File tempFile = new File(context.getFilesDir() + "tempfile");
        Timber.d(tempFile.toString());
        try {
            out = new FileOutputStream(tempFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byte[] buf = new byte[1024*4];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            Timber.e(e);
        }

        JSONObject data = new JSONObject();
        JSONArray tags = new JSONArray();
        try {
            data.put("test", "test123");
            tags.put(1);
            tags.put(2);
            tags.put(3);

            data.put("tags", tags);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), tempFile);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", tempFile.getName(), reqFile);
       // val userID = RequestBody.create(MediaType.parse("multipart/form-data"), id)

        RequestBody d = MultipartBody.create(MediaType.parse("text/plain"), data.toString());

        return service.uploadCritiqImage(d, body);
    }
}
