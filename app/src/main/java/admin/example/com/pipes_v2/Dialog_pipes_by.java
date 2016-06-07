package admin.example.com.pipes_v2;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

public class Dialog_pipes_by extends DialogFragment
{//тэг для передачи результата обратно
        public static final String TAG_WEIGHT_SELECTED = "weight";
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_pipes_by,null);
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            final CheckBox checkBox_min = (CheckBox) view.findViewById(R.id.checkBox_min);
            final CheckBox checkBox_max = (CheckBox) view.findViewById(R.id.checkBox_max);
            checkBox_min.setText(getArguments().getString("min"));
            checkBox_max.setText(getArguments().getString("max"));
            checkBox_min.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(TAG_WEIGHT_SELECTED, Integer.parseInt(checkBox_min.getText().toString()));
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                }
            });
            checkBox_max.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(TAG_WEIGHT_SELECTED, Integer.parseInt(checkBox_max.getText().toString()));
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                }
            });
            builder.setView(view)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //отправляем результат обратно
                            //Intent intent = new Intent();
                            //intent.putExtra(TAG_WEIGHT_SELECTED, 150);
                            //getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
                        }
                    });
            return builder.create();
        }

}
