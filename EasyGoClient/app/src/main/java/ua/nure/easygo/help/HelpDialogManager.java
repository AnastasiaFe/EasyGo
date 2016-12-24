package ua.nure.easygo.help;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import easygo.nure.ua.easygoclient.R;

/**
 * Created by Oleg on 24.12.2016.
 */

public class HelpDialogManager {
    public static void showHelp(final Class clas, final Context context) {

        if (ShowManager.show(context, clas)) {
            HelpItem helpItem = HelpLoader.load(context, clas);
            if (helpItem == null) {
                return;
            }

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = layoutInflater.inflate(R.layout.diaog_help, null);
            ImageView imageView = (ImageView) v.findViewById(R.id.dialog_image);
            imageView.setImageBitmap(helpItem.getBmp());

            TextView textView = (TextView) v.findViewById(R.id.dialog_text);
            textView.setText(helpItem.getMessage());

            AlertDialog alertDialog = new AlertDialog.Builder(context).setView(v)
                    .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Remind later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            ShowManager.showAgainLater(context, clas);
                        }
                    })
                    .create();
            alertDialog.show();
        }
    }
}
