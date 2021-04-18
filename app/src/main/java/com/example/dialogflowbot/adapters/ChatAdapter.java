package com.example.dialogflowbot.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dialogflowbot.R;
import com.example.dialogflowbot.models.Message;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

  private List<Message> messageList;
  private Activity activity;

  public ChatAdapter(List<Message> messageList, Activity activity) {
    this.messageList = messageList;
    this.activity = activity;
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //전개
    View view = LayoutInflater.from(activity).inflate(R.layout.adapter_message_one, parent, false);
    return new MyViewHolder(view);
  }

  // 전송한 msg인지, 받은 msg인지 체크해서 말풍선 추가
  @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String message = messageList.get(position).getMessage();
    boolean isReceived = messageList.get(position).getIsReceived();
     if(isReceived){
       if (message.substring(0, 2).equals("id")){
         String[] msg_lst = message.split(" ");
         for(int i = 0; i < msg_lst.length; i++){
           Log.i("msg_lst", msg_lst[i]);
         } // "id 날씨 0 xx"
         int id = Integer.parseInt(msg_lst[2]);
         switch(id){
           case 1:
             message = "맑음";
             break;
           case 2:
             message = "흐림";
             break;
           case 3:
             message = "비";
             break;
           case 4:
             message = "눈";
             break;
           default:
             message = "정보 없음";
             break;
         }
       }
       Log.i("received message >>", message);

       holder.messageReceive.setVisibility(View.VISIBLE);
       holder.messageSend.setVisibility(View.GONE);
       holder.messageReceive.setText(message);
     }else {
       holder.messageSend.setVisibility(View.VISIBLE);
       holder.messageReceive.setVisibility(View.GONE);
       holder.messageSend.setText(message);
     }
  }

  @Override public int getItemCount() {
    return messageList.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder{

    TextView messageSend;
    TextView messageReceive;

    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      messageSend = itemView.findViewById(R.id.message_send);
      messageReceive = itemView.findViewById(R.id.message_receive);
    }
  }

}