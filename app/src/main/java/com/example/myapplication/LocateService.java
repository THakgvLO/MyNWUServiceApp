package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

import ch.qos.logback.classic.Logger;

public class LocateService extends Fragment {

    private static final String ARG_PDF_PATH = "pdf_path"; // Key for PDF path argument
    private static final Logger logger = (Logger) LoggerFactory.getLogger(LocateServicesFragment.class);

    public static LocateService newInstance(String pdfPath) {
        LocateService fragment = new LocateService();
        Bundle args = new Bundle();
        args.putString(ARG_PDF_PATH, pdfPath); // Pass the PDF path to the fragment
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_locate_services, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView imageView = view.findViewById(R.id.locateServiceButton);

        assert getArguments() != null;
        String pdfPath = getArguments().getString(ARG_PDF_PATH);
        if (pdfPath != null) {
            displayPdfPageAsImage(pdfPath, imageView); // Load the PDF page as an image
        }
    }

    private void displayPdfPageAsImage(String pdfPath, ImageView imageView) {
        try {
            ParcelFileDescriptor fileDescriptor = ParcelFileDescriptor.open(new File(pdfPath), ParcelFileDescriptor.MODE_READ_ONLY);
            PdfRenderer pdfRenderer = new PdfRenderer(fileDescriptor);

            // Assuming you want to display the first page
            PdfRenderer.Page page = pdfRenderer.openPage(0);

            // Create a bitmap to render the page
            Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);

            // Render the page into the bitmap
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

            // Set the bitmap to the ImageView
            imageView.setImageBitmap(bitmap);

            // Close the page and renderer
            page.close();
            pdfRenderer.close();
        } catch (IOException e) {
             String message = e.getMessage();
        }
    }
}
