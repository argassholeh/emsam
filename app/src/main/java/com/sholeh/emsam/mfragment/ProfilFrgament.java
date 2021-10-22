/*
 * Copyright (c) Muhammad Solehudin
 */

package com.sholeh.emsam.mfragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.sholeh.emsam.ActivityLogin;
import com.sholeh.emsam.Helper.Preferences;
import com.sholeh.emsam.R;
import com.sholeh.emsam.cetakpdf.PdfDocumentAdapter;
import com.sholeh.emsam.profil.Common;
import com.sholeh.emsam.profil.ProfilActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;


public class ProfilFrgament extends Fragment {
    private KProgressHUD progressDialogHud;
    Preferences preferences;
    ImageView imgtoolbar;
    TextView tvxTitle;

    private TextView nama, pekerja, tglmulai, ttl, sim, nomorkartupengenal, agama, jeniskelamin,
            status, formal, sertifikasi, kesehatan, ketenagakerjaan, nohp, alamat;
    private FloatingActionButton download, print;


    Bitmap bitmap, scaleBitmap;
    int pageWidth = 1200;
    Date dateTime;
    DateFormat dateFormat;

    ImageView imageView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profil,container,false);
        progressDialogHud = KProgressHUD.create(getActivity());
        preferences = new Preferences(getActivity());


        nama = rootView.findViewById(R.id.tvnamapekerja);
        pekerja = rootView.findViewById(R.id.tvnamapekerja);
        tglmulai = rootView.findViewById(R.id.tvtglmulai);
        ttl = rootView.findViewById(R.id.tvttl);
        sim = rootView.findViewById(R.id.tvsim);
        nomorkartupengenal = rootView.findViewById(R.id.tvnopengenal);
        agama = rootView.findViewById(R.id.tvagama);
        jeniskelamin = rootView.findViewById(R.id.tvjk);
        status = rootView.findViewById(R.id.tvstatus);
        formal = rootView.findViewById(R.id.tvformal);
        sertifikasi = rootView.findViewById(R.id.tvsertifikat);
        kesehatan = rootView.findViewById(R.id.tvkesehatan);
        ketenagakerjaan = rootView.findViewById(R.id.tvketenagakerjaan);
        nohp = rootView.findViewById(R.id.tvnohp);
        alamat = rootView.findViewById(R.id.tvalamat);

        imageView = rootView.findViewById(R.id.imgkaryawan);

    
        print = rootView.findViewById(R.id.fbdownload);


        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        print.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                uploadimage();
                                createPDFFile(Common.getAppPath(getActivity()) + "CreatePdf.pdf");
                            }
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 518, false);

        //permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        return rootView;
    }

    private void ProgresDialog() {
        progressDialogHud.setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false);
        progressDialogHud.show();
    }


    private void createPDFFile(String path) {
        if (new File(path).exists())
            new File(path).delete();
        try {
            Document document = new Document();
            //save
            PdfWriter.getInstance(document, new FileOutputStream(path));
            //open to write
            document.open();

            //setting

            Drawable d = getActivity().getDrawable(R.drawable.ic_address);
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapData = stream.toByteArray();
            Image image = Image.getInstance(bitmapData);

//            com.itextpdf.layout.element.Image image1 = new com.itextpdf.layout.element.Image(image);
            document.add(image);
            document.setPageSize(PageSize.A4);
            document.addCreationDate();
            document.addAuthor("EMSAM");
            document.addCreator("M. Sholehuddin");


            //font setting
            BaseColor colorAccent = new BaseColor(0, 153, 204, 255);
            float fontSize = 20.0f;
            float valueFontSize = 26.0f;


            BaseFont fontName = BaseFont.createFont("assets/fonts/brandon_bold.otf", "UTF-8", BaseFont.EMBEDDED);

            //create title of document

            Font titleFont = new Font(fontName, 36.0f, Font.NORMAL, BaseColor.BLACK);
            addNewItem(document, nama.getText().toString(), Element.ALIGN_CENTER, titleFont);

            //format paragraf
            /*Font orderNumberFont = new Font(fontName, fontSize, Font.NORMAL, colorAccent);
            addNewItem(document, "Jabatan : ", Element.ALIGN_LEFT, orderNumberFont);


            Font orderNumberValueFont = new Font(fontName, fontSize, Font.NORMAL, BaseColor.BLACK);
            addNewItem(document, pekerja.getText().toString(), Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeperator(document);

             addNewItem(document, "Tanggal Mulai Tugas", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, "11-12-2021", Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeperator(document);

            addNewItem(document, "Kartu Pengenal", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, sim.getText().toString(), Element.ALIGN_LEFT, orderNumberValueFont);

            addLineSeperator(document);

            */


            //font untuk header dan sub
            Font orderNumberValueFont = new Font(fontName, fontSize, Font.NORMAL, BaseColor.BLACK);
            Font orderNumberFont = new Font(fontName, fontSize, Font.NORMAL, colorAccent);

            //format kesamping
            addNewItemWithLeftAndRightSumItem(document, "JABATAN :", pekerja.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "TEMPAT TANGGAL LAHIR :", ttl.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "NOMOR HP :", nohp.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "ALAMAT :", alamat.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "TANGGAL MULAI TUGAS :", tglmulai.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "KARTU PENGENAL :", sim.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "AGAMA :", agama.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "JENIS KELAMIN :", jeniskelamin.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "STATUS :", status.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

            addNewItemWithLeftAndRightSumItem(document, "PENDIDIKAN FORMAL :", formal.getText().toString(), orderNumberFont, orderNumberValueFont);
            addLineSeperator(document);

//            addNewItemWithLeftAndRightSumItem(document, "SERTIFIKASI / KETERAMPILAN :", sertifikasi.getText().toString(), orderNumberFont, orderNumberValueFont);
//            addLineSeperator(document);

//            addNewItemWithLeftAndRightSumItem(document, "NOMOR KEPESERTAAN BPJS KESEHATAN :", kesehatan.getText().toString(), orderNumberFont, orderNumberValueFont);
//            addLineSeperator(document);
//
//            addNewItemWithLeftAndRightSumItem(document, "NOMOR KEPESERTAAAN BPJS KETENAGAKERJAAN :", ketenagakerjaan.getText().toString(), orderNumberFont, orderNumberValueFont);
//            addLineSeperator(document);

            addNewItem(document, "SERTIFIKASI / KETERAMPILAN :", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, sertifikasi.getText().toString(), Element.ALIGN_LEFT, orderNumberValueFont);
            addLineSeperator(document);

            addNewItem(document, "NOMOR KEPESERTAAN BPJS KESEHATAN :", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, kesehatan.getText().toString(), Element.ALIGN_LEFT, orderNumberValueFont);
            addLineSeperator(document);

            addNewItem(document, "NOMOR KEPESERTAAN BPJS KETENAGAKERJAAN :", Element.ALIGN_LEFT, orderNumberFont);
            addNewItem(document, ketenagakerjaan.getText().toString(), Element.ALIGN_LEFT, orderNumberValueFont);
            addLineSeperator(document);


            //add product order detail
            addLineSpace(document);
            addNewItem(document, "Product Detail", Element.ALIGN_CENTER, titleFont);
            addLineSeperator(document);

            //item
            addNewItemWithLeftAndRight(document, "Kentang", "(0.0%)", titleFont, orderNumberValueFont);
            addNewItemWithLeftAndRight(document, "12.000*10000", "12.0000.0", titleFont, orderNumberValueFont);

            addLineSeperator(document);

            //itemkedua
            addNewItemWithLeftAndRight(document, "BABUL Khoir", "(0.0%)", titleFont, orderNumberValueFont);
            addNewItemWithLeftAndRight(document, "12.000*10000", "12.0000.0", titleFont, orderNumberValueFont);

            addLineSeperator(document);

            //total
            addLineSpace(document);
            addLineSpace(document);

            addNewItemWithLeftAndRight(document, "Total", "22.0000.0", titleFont, orderNumberValueFont);

            document.close();

            Toast.makeText(getActivity(), "SUKSES", Toast.LENGTH_SHORT).show();

            printPDF();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void printPDF() {
        PrintManager printManager = (PrintManager) getActivity().getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(getActivity(), Common.getAppPath(getActivity()) + "CreatePdf.pdf");
            printManager.print("document", printDocumentAdapter, new PrintAttributes.Builder().build());

        } catch (Exception ex) {
            Log.e("TEST", "" + ex.getMessage());

        }
    }

    private void addNewItemWithLeftAndRight(Document document, String textleft, String textright, Font textleftFont, Font textrightFont) throws DocumentException {
        Chunk chunkTextLeft = new Chunk(textleft, textleftFont);
        Chunk chunkTextRight = new Chunk(textright, textrightFont);
        Paragraph p = new Paragraph(chunkTextLeft);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextRight);
        document.add(p);

    }


    private void addNewItemWithLeftAndRightSumItem(Document document, String textleft, String textright, Font textleftFont, Font textrightFont) throws DocumentException {
        Chunk chunkTextLeft = new Chunk(textleft, textleftFont);
        Chunk chunkTextRight = new Chunk(textright, textrightFont);
        Paragraph p = new Paragraph(chunkTextLeft);
        p.add(new Chunk(new VerticalPositionMark()));
        p.add(chunkTextRight);
        document.add(p);

    }

    private void addLineSeperator(Document document) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(new BaseColor(0, 0, 0, 68));
        addLineSpace(document);
        document.add(new Chunk(lineSeparator));
        addLineSpace(document);
    }

    private void addLineSpace(Document document) throws DocumentException {
        document.add(new Paragraph(""));

    }

    private void addNewItem(Document document, String text, int align, Font font) throws DocumentException {
        Chunk chunk = new Chunk(text, font);
        Paragraph paragraph = new Paragraph(chunk);
        paragraph.setAlignment(align);
        document.add(paragraph);
    }

    public void logoutAkun() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Apakah anda yakin, ingin logout?");
        builder.setCancelable(true);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProgresDialog();
                preferences.saveSPBoolean(preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getActivity(), ActivityLogin.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                getActivity().finish();
                Toast.makeText(getActivity(), "Berhasil Keluar", Toast.LENGTH_LONG).show();
                progressDialogHud.dismiss();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }





}

