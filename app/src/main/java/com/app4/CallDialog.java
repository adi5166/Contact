package com.app4;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

    public class CallDialog extends DialogFragment {

        private CallDialog.OnCallDialogInteractionListener mListener;

        public CallDialog() {
            // Required empty public constructor
        }
        static CallDialog newInstance(){
            return new CallDialog();
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            // AlertDialog.Builder will be used to create the dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            // Set the message displayed in the dialog
            //TODO dodać imię do połączenia
            builder.setMessage(getString(R.string.call_question));
            // Set the text and action for the positive button click
            builder.setPositiveButton(getString(R.string.call_confirm), new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Notify the OnDeleteDialogInteractionListener interface of positive button click
                    mListener.OnCallPositiveClick(CallDialog.this);
                }
            });
            // Set the text and action for the negative button click
            builder.setNegativeButton(getString(R.string.call_cancel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Notify the OnDeleteDialogInteractionListener interface of negative button click
                    mListener.OnCallNegativeClick(CallDialog.this);
                }
            });
            return builder.create();
        }
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof CallDialog.OnCallDialogInteractionListener) {
                mListener = (CallDialog.OnCallDialogInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnDeleteDialogInteractionListener");
            }
        }

        @Override
        public void onDetach() {
            super.onDetach();
            mListener = null;
        }

        public interface OnCallDialogInteractionListener {
            void OnCallPositiveClick(DialogFragment dialog);
            void OnCallNegativeClick(DialogFragment dialog);
        }
    }

