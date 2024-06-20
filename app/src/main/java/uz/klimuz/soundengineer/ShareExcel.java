package uz.klimuz.soundengineer;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;

import java.io.File;

public class ShareExcel {
    private final String FOLDER = "NewFolder";

    public static Intent saveExcel(Workbook workbook, Context context) {
        final String FILENAME = " input list.xls";
        String filepath = Environment.getExternalStorageDirectory().toString() + File.separator +
                "Documents" + File.separator + ChooseStageBoxActivity.projectName + FILENAME;
        if (Build.VERSION.SDK_INT < 30) {
            filepath = context.getExternalFilesDir(null).toString() + File.separator + ChooseStageBoxActivity.projectName
                    + FILENAME;
        }
        try {
            workbook.save(filepath, FileFormatType.EXCEL_97_TO_2003);
        } catch (Exception e) {
            Log.i("myLog", "error saving workbook : " + e);
            throw new RuntimeException(e);
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        File file = new File(filepath);
        file.setReadable(true, false);
        try {
            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", file);

            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("application/vnd.ms-excel");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } catch (IllegalArgumentException e) {
            Log.i("myLog", "Error while providing attachment: " + file, e);
        }
        return intent;
    }
}