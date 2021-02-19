package com.nikolam.feature_upload.presenter;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.nikolam.feature_upload.R;
import com.nikolam.feature_upload.databinding.UploadFragmentBinding;
import com.nikolam.feature_upload.presenter.tags.TagAdapter;
import com.nikolam.feature_upload.presenter.tags.TagModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class UploadFragment extends Fragment implements SortedListAdapter.Callback {

    private static final int CAMERA_REQUEST = 1888;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private UploadViewModel viewModel;

    String currentPhotoPath;
    Uri currentPhotoURI;

    private UploadFragmentBinding binding;

    private TagAdapter tagAdapter;
    private TagAdapter selectedAdapter;
    private Animator animator;

    private List<TagModel> tags;
    private List<TagModel> selectedTags;

    private static final Comparator<TagModel> COMPARATOR = new SortedListAdapter.ComparatorBuilder<TagModel>()
            .setOrderForModel(TagModel.class, (a, b) -> a.getTag().compareTo(b.getTag()))
            .build();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = UploadFragmentBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        setupImagePicker();
        setupCamera();

        binding.uploadButton.setOnClickListener(v -> {
            viewModel.uploadPost(currentPhotoURI, selectedTags, binding.commentEditText.getText().toString());
        });

        //    Tags

        tagAdapter = new TagAdapter(requireContext(), COMPARATOR, model -> {
            if (selectedTags.size() >= 5) {
                return;
            }
            tags.remove(model);
            selectedTags.add(model);

            tagAdapter.edit()
                    .replaceAll(tags)
                    .commit();

            selectedAdapter.edit()
                    .replaceAll(selectedTags)
                    .commit();

            binding.tagFilterEditText.setText("");
            hideSoftKeyboard(getActivity());
        });

        selectedAdapter = new TagAdapter(requireContext(), COMPARATOR, model -> {
            selectedTags.remove(model);
            tags.add(model);

            tagAdapter.edit()
                    .replaceAll(tags)
                    .commit();

            selectedAdapter.edit()
                    .replaceAll(selectedTags)
                    .commit();
        });

        tagAdapter.addCallback(this);
        selectedAdapter.addCallback(this);

        binding.tagOptionsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.tagOptionsRecyclerView.setAdapter(tagAdapter);

        binding.selectedTagsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)); //new GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, true));
        binding.selectedTagsRecyclerView.setAdapter(selectedAdapter);


        tags = new ArrayList<>();
        selectedTags = new ArrayList<>();
        final String[] tag_array = getResources().getStringArray(R.array.tag_options_array);
        for (int i = 0; i < tag_array.length; i++) {
            tags.add(new TagModel(i, tag_array[i]));
        }
        tagAdapter.edit()
                .replaceAll(tags)
                .commit();

        binding.tagFilterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final List<TagModel> filteredModelList = filter(tags, s.toString());
                tagAdapter.edit()
                        .replaceAll(filteredModelList)
                        .commit();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(UploadViewModel.class);
    }

    private void setupImagePicker() {
        binding.uploadFromGalleryButton.setOnClickListener(v -> {
            requestStoragePermission();
            ImagePicker.create(this)
                    .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                    .toolbarFolderTitle("Gallery") // folder selection title
                    .toolbarImageTitle("Tap an image to select") // image selection title
                    .includeVideo(false) // Show video on image picker
                    .single() // single mode
                    .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                    .enableLog(true) // disabling log
                    .start(); // start image picker activity with request code
        });
    }

    private void setupCamera() {
        binding.uploadFromCameraButton.setOnClickListener(v -> {
            requestStoragePermission();
            requestCameraPermission();
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Ensure that there's a camera activity to handle the intent
            if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                // Create the File where the photo should go
                File photoFile = null;
                try {
                    photoFile = createImageFile();
                } catch (IOException ex) {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(requireContext(),
                            "com.nikolam.critiq.fileprovider",
                            photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    currentPhotoURI = photoURI;
                    currentPhotoPath = photoFile.getAbsolutePath();
                    Glide.with(binding.selectedImage).load(photoURI).into(binding.selectedImage);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            Image image = ImagePicker.getFirstImageOrNull(data);
            if (image == null) {
                return;
            }

            currentPhotoURI = image.getUri();
            currentPhotoPath = image.getPath();
            Glide.with(binding.selectedImage).load(image.getUri()).into(binding.selectedImage);
        }
        Timber.d(currentPhotoURI.toString());

        super.onActivityResult(requestCode, resultCode, data);

    }

    private void requestStoragePermission() {
        Dexter.withContext(getContext())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (ActivityCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return;
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(
                                getContext(),
                                "You won't be able upload images without this permission",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private void requestCameraPermission() {
        Dexter.withContext(getContext())
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (ActivityCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            return;
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(
                                getContext(),
                                "You won't be able upload images without this permission",
                                Toast.LENGTH_LONG
                        ).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private static List<TagModel> filter(List<TagModel> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<TagModel> filteredModelList = new ArrayList<>();
        for (TagModel model : models) {
            final String text = model.getTag().toLowerCase();
            if (text.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    public static void hideSoftKeyboard(Activity activity) {
        if (activity.getCurrentFocus() == null) {
            return;
        }
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }


    @Override
    public void onDestroyView() {
        binding = null;
        super.onDestroyView();
    }

    @Override
    public void onEditStarted() {
        if (binding.editProgressBar.getVisibility() != View.VISIBLE) {
            binding.editProgressBar.setVisibility(View.VISIBLE);
            binding.editProgressBar.setAlpha(0.0f);
        }

        if (animator != null) {
            animator.cancel();
        }

        animator = ObjectAnimator.ofFloat(binding.editProgressBar, View.ALPHA, 1.0f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();

        binding.tagOptionsRecyclerView.animate().alpha(0.5f);
    }

    @Override
    public void onEditFinished() {
        binding.tagOptionsRecyclerView.scrollToPosition(0);
        binding.tagOptionsRecyclerView.animate().alpha(1.0f);

        if (animator != null) {
            animator.cancel();
        }

        animator = ObjectAnimator.ofFloat(binding.editProgressBar, View.ALPHA, 0.0f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {

            private boolean mCanceled = false;

            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
                mCanceled = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (!mCanceled) {
                    binding.editProgressBar.setVisibility(View.GONE);
                }
            }
        });
        animator.start();
    }
}