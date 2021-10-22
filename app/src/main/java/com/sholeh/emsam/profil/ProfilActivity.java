package com.sholeh.emsam.profil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import com.itextpdf.text.pdf.interfaces.PdfDocumentActions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.sholeh.emsam.R;

import com.itextpdf.text.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sholeh.emsam.cetakpdf.PdfDocumentAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProfilActivity extends AppCompatActivity {
    private TextView nama, pekerja, tglmulai, ttl, sim, nomorkartupengenal, agama, jeniskelamin,
            status, formal, sertifikasi, kesehatan, ketenagakerjaan, nohp, alamat;

    private FloatingActionButton download, print;


    Bitmap bitmap, scaleBitmap;
    int pageWidth = 1200;
    Date dateTime;
    DateFormat dateFormat;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nama = findViewById(R.id.tvnamapekerja);
        pekerja = findViewById(R.id.tvnamapekerja);
        tglmulai = findViewById(R.id.tvtglmulai);
        ttl = findViewById(R.id.tvttl);
        sim = findViewById(R.id.tvsim);
        nomorkartupengenal = findViewById(R.id.tvnopengenal);
        agama = findViewById(R.id.tvagama);
        jeniskelamin = findViewById(R.id.tvjk);
        status = findViewById(R.id.tvstatus);
        formal = findViewById(R.id.tvformal);
        sertifikasi = findViewById(R.id.tvsertifikat);
        kesehatan = findViewById(R.id.tvkesehatan);
        ketenagakerjaan = findViewById(R.id.tvketenagakerjaan);
        nohp = findViewById(R.id.tvnohp);
        alamat = findViewById(R.id.tvalamat);

        imageView = findViewById(R.id.imgkaryawan);

        download = findViewById(R.id.fbdownload);
        print = findViewById(R.id.fb_print);


        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        print.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                uploadimage();
                                createPDFFile(Common.getAppPath(ProfilActivity.this) + "CreatePdf.pdf");
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
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

//        createPDF();

    }

//    private void uploadimage() {
//                Document doc = new Document();
//                try {
//                    PdfWriter.getInstance(doc, new FileOutputStream("ImageDemo.pdf"));
//                    doc.open();
//
//                    // Creating image by file name
//                    String filename = "other-sample/src/main/resources/java.gif";
//                    Image image = Image.getInstance(filename);
//                    doc.add(image);
//
//                    // The following line to prevent the "Server returned
//                    // HTTP response code: 403" error.
//                    System.setProperty("http.agent", "Chrome");
//
//                    // Creating image from a URL
//                    String url = "https://kodejava.org/wp-content/uploads/2017/01/kodejava.png";
//                    image = Image.getInstance(url);
//                    doc.add(image);
//                } catch (DocumentException | IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    doc.close();
//                }
//            }

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

            Drawable d = getDrawable(R.drawable.ic_address);
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

            Toast.makeText(this, "SUKSES", Toast.LENGTH_SHORT).show();

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
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        try {
            PrintDocumentAdapter printDocumentAdapter = new PdfDocumentAdapter(ProfilActivity.this, Common.getAppPath(ProfilActivity.this) + "CreatePdf.pdf");
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

    private void createPDF() {

        download.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View v) {

                dateTime = new Date();

                if (nama.getText().toString().length() == 0 ||
                        pekerja.getText().toString().length() == 0 ||
                        tglmulai.getText().toString().length() == 0 ||
                        ttl.getText().toString().length() == 0 ||
                        sim.getText().toString().length() == 0 ||
                        nomorkartupengenal.getText().toString().length() == 0 ||
                        agama.getText().toString().length() == 0 ||
                        jeniskelamin.getText().toString().length() == 0 ||
                        status.getText().toString().length() == 0 ||
                        formal.getText().toString().length() == 0 ||
                        sertifikasi.getText().toString().length() == 0 ||
                        kesehatan.getText().toString().length() == 0 ||
                        ketenagakerjaan.getText().toString().length() == 0 ||
                        nohp.getText().toString().length() == 0 ||

                        alamat.getText().toString().length() == 0) {
                    Toast.makeText(ProfilActivity.this, "Data tidak boleh kosong!", Toast.LENGTH_LONG).show();
                } else {

                    PdfDocument pdfDocument = new PdfDocument();
                    Paint paint = new Paint();
                    Paint titlePaint = new Paint();

                    PdfDocument.PageInfo pageInfo
                            = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                    PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                    Canvas canvas = page.getCanvas();
                    canvas.drawBitmap(scaleBitmap, 0, 0, paint);

                    paint.setColor(Color.WHITE);
                    paint.setTextSize(30f);
                    paint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText("Data Profil Karyawan", 1160, 40, paint);
////                    canvas.drawText("Pesan di : 08123456789", 1160, 80, paint);

                    titlePaint.setTextAlign(Paint.Align.CENTER);
                    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                    titlePaint.setColor(Color.WHITE);
                    titlePaint.setTextSize(70);
                    canvas.drawText("Data Karyawan", pageWidth / 2, 500, titlePaint);

                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setColor(Color.BLACK);
                    paint.setTextSize(35f);
                    canvas.drawText("Nama Karyawan: " + nama.getText(), 20, 590, paint);
                    canvas.drawText("Nomor HP: " + nohp.getText(), 20, 640, paint);

                    paint.setTextAlign(Paint.Align.RIGHT);
                    canvas.drawText("No. Pesanan: " + "232425", pageWidth - 20, 590, paint);

                    dateFormat = new SimpleDateFormat("dd/MM/yy");
                    canvas.drawText("Tanggal: " + dateFormat.format(dateTime), pageWidth - 20, 640, paint);

                    dateFormat = new SimpleDateFormat("HH:mm:ss");
                    canvas.drawText("Pukul: " + dateFormat.format(dateTime), pageWidth - 20, 690, paint);

                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(2);
                    canvas.drawRect(20, 780, pageWidth - 20, 860, paint);

                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawText("No.", 40, 830, paint);
                    canvas.drawText("Menu Pesanan", 200, 830, paint);
                    canvas.drawText("Harga", 700, 830, paint);
                    canvas.drawText("Jumlah", 900, 830, paint);
                    canvas.drawText("Total", 1050, 830, paint);

                    canvas.drawLine(180, 790, 180, 840, paint);
                    canvas.drawLine(680, 790, 680, 840, paint);
                    canvas.drawLine(880, 790, 880, 840, paint);
                    canvas.drawLine(1030, 790, 1030, 840, paint);

//                    float totalOne = 0, totalTwo = 0;
//                    if (itemSpinnerOne.getSelectedItemPosition() != 0) {
//                        canvas.drawText("1.", 40, 950, paint);
//                        canvas.drawText(itemSpinnerOne.getSelectedItem().toString(), 200, 950, paint);
//                        canvas.drawText(String.valueOf(harga[itemSpinnerOne.getSelectedItemPosition()]), 700, 950, paint);
//                        canvas.drawText(etJmlOne.getText().toString(), 900, 950, paint);
//                        totalOne = Float.parseFloat(etJmlOne.getText().toString()) * harga[itemSpinnerOne.getSelectedItemPosition()];
//                        paint.setTextAlign(Paint.Align.RIGHT);
//                        canvas.drawText(String.valueOf(totalOne), pageWidth - 40, 950, paint);
//                        paint.setTextAlign(Paint.Align.LEFT);
//                    }
//
//                    if (itemSpinnerTwo.getSelectedItemPosition() != 0) {
//                        canvas.drawText("2.", 40, 1050, paint);
//                        canvas.drawText(itemSpinnerTwo.getSelectedItem().toString(), 200, 1050, paint);
//                        canvas.drawText(String.valueOf(harga[itemSpinnerTwo.getSelectedItemPosition()]), 700, 1050, paint);
//                        canvas.drawText(etJmlTwo.getText().toString(), 900, 1050, paint);
//                        totalTwo = Float.parseFloat(etJmlTwo.getText().toString()) * harga[itemSpinnerTwo.getSelectedItemPosition()];
//                        paint.setTextAlign(Paint.Align.RIGHT);
//                        canvas.drawText(String.valueOf(totalTwo), pageWidth - 40, 1050, paint);
//                        paint.setTextAlign(Paint.Align.LEFT);
//                    }
//
//                    float subTotal = totalOne + totalTwo;
//                    canvas.drawLine(400, 1200, pageWidth - 20, 1200, paint);
//                    canvas.drawText("Sub Total", 700, 1250, paint);
//                    canvas.drawText(":", 900, 1250, paint);
//                    paint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText(String.valueOf(subTotal), pageWidth - 40, 1250, paint);
//
//                    paint.setTextAlign(Paint.Align.LEFT);
//                    canvas.drawText("PPN (10%)", 700, 1300, paint);
//                    canvas.drawText(":", 900, 1300, paint);
//                    paint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText(String.valueOf(subTotal * 10 / 100), pageWidth - 40, 1300, paint);
//                    paint.setTextAlign(Paint.Align.LEFT);
//
//                    paint.setColor(Color.rgb(247, 147, 30));
//                    canvas.drawRect(680, 1350, pageWidth - 20, 1450, paint);
//
//                    paint.setColor(Color.BLACK);
//                    paint.setTextSize(50f);
//                    paint.setTextAlign(Paint.Align.LEFT);
//                    canvas.drawText("Total", 700, 1415, paint);
//                    paint.setTextAlign(Paint.Align.RIGHT);
//                    canvas.drawText(String.valueOf(subTotal + (subTotal * 10 / 100)), pageWidth - 40, 1415, paint);


                }
            }
        });
    }
}