package com.educapyoficial.educapy;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.models.modelVideo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;

public class AdapterVideo extends RecyclerView.Adapter<AdapterVideo.HolderVideo> {

    private Context context;

    //lista arreglo
    private ArrayList<modelVideo> videoArrayList;

    public AdapterVideo(Context context, ArrayList<modelVideo> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public HolderVideo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_video, parent, false);


        return new HolderVideo(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderVideo holder, int position) {

        final modelVideo modelVideo = videoArrayList.get(position);

        String id = modelVideo.getId();
        final String title = modelVideo.getTitle();
        final String timestamp = modelVideo.getTimestamp();
        String videoUrl = modelVideo.getVideoUrl();

        //formato del calendario e.g 07/12/2020 01:53 PM

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(timestamp));
        String formattedDateTime = DateFormat.format("dd/MM/yyyy K:mm a", calendar).toString();

        holder.titleTv.setText(title);
        holder.timeTv.setText(formattedDateTime);
        setVideoUrl(modelVideo, holder);

        holder.downloadFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                downloadVideo(modelVideo);
                Toast.makeText(context, "Descargando video progreso en pestaña superior", Toast.LENGTH_SHORT).show();
            }
        });

        holder.deletefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete")
                        .setMessage("Desea Borrar el Video? " + title)
                        .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteVideo(modelVideo);
                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                //cancelar el borrado ,desaparecer dialogo
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    private void setVideoUrl(final modelVideo modelVideo, final HolderVideo holder) {

        holder.progressBar.setVisibility(View.VISIBLE);

        String videoUrl = modelVideo.getVideoUrl();

        MediaController mediaController = new MediaController(context);
        mediaController.setAnchorView(holder.videoView);

        Uri videoUri = Uri.parse(videoUrl);
        holder.videoView.setMediaController(mediaController);
        holder.videoView.setVideoURI(videoUri);

        holder.videoView.requestFocus();
        holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });

        holder.videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {

                switch (what) {
                    case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return true;
                    }
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        return true;
                    }
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
                        holder.progressBar.setVisibility(View.GONE);
                        return true;
                    }
                }
                return false;
            }
        });

        holder.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });


    }

    private void deleteVideo(modelVideo modelVideo) {
        final String videoId = modelVideo.getId();
        String videoUrl = modelVideo.getVideoUrl();

        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(videoUrl);
        reference.delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Videos");
                        databaseReference.child(videoId)
                                .removeValue()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        //borrado en firebase
                                        Toast.makeText(context, "Video Borrado", Toast.LENGTH_SHORT).show();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //fallo al borrar en firebase
                                        Toast.makeText(context, "Error al borrar codigo " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Error code " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void downloadVideo(modelVideo modelVideo) {

        final String videoUrl = modelVideo.getVideoUrl(); //videoUrl tiene la liga del video se utilizara para descargar el video

        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(videoUrl);
        storageReference.getMetadata()
                .addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                    @Override
                    public void onSuccess(StorageMetadata storageMetadata) {
                        String fileName = storageMetadata.getName(); //nombre del archivo en firebase
                        String fileType = storageMetadata.getContentType(); //tipo de archivo en firebase
                        String fileDirectory = Environment.DIRECTORY_DOWNLOADS; //directorio del archivo donde se guardara el video

                        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(videoUrl);

                        DownloadManager.Request request = new DownloadManager.Request(uri);

                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalPublicDir("" + fileDirectory, "" + fileName + ".mp4");

                        downloadManager.enqueue(request);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(context, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size(); //obtiene el tamaño de la lista de videos
    }

    //constructor
    class HolderVideo extends RecyclerView.ViewHolder {

        VideoView videoView;
        TextView titleTv, timeTv;
        ProgressBar progressBar;
        FloatingActionButton deletefab, downloadFab;

        public HolderVideo(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.videoviewScreen);
            titleTv = itemView.findViewById(R.id.titleTv);
            timeTv = itemView.findViewById(R.id.timetv);
            progressBar = itemView.findViewById(R.id.progressBarRT);

            deletefab = itemView.findViewById(R.id.deleteFabRT);
            downloadFab = itemView.findViewById(R.id.downloadFabRT);
        }
    }
}
