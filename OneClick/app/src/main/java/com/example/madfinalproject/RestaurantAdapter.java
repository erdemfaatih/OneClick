package com.example.madfinalproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder>implements IRestaurantInterface{
    IRestaurantInterface iRestaurantInterface;
    Context context;
    ArrayList<Restaurant> restaurantArrayList;
    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantArrayList, IRestaurantInterface iRestaurantInterface) {
        this.restaurantArrayList = restaurantArrayList;
        this.context = context;
        this.iRestaurantInterface = iRestaurantInterface;
    }
    @NonNull
    @Override
    public RestaurantAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.restaurantitem,parent,false);
        return new MyViewHolder(v, iRestaurantInterface);
    }
    @Override
    public void onBindViewHolder(@NonNull RestaurantAdapter.MyViewHolder holder, int position) {
        Restaurant restaurant = restaurantArrayList.get(position);
        holder.name.setText(restaurant.getName());
        holder.surname.setText(restaurant.getDensity());
        holder.age.setText(restaurant.getDistance());
    }
    @Override
    public int getItemCount() {
        return restaurantArrayList.size();
    }

    @Override
    public void onItemClicked(int position) {
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        IRestaurantInterface iRestaurantInterface;
        TextView name, surname, age;
        public MyViewHolder(@NonNull View itemView, IRestaurantInterface iRestaurantInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.txtRestaurantsName);
            surname = itemView.findViewById(R.id.txtRestaurantsDensity);
            age = itemView.findViewById(R.id.txtRestaurantsDistance);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(iRestaurantInterface !=null){
                        int position= getAdapterPosition();
                        if(position !=RecyclerView.NO_POSITION)
                            iRestaurantInterface.onItemClicked(position);
                    }
                }
            });
        }
    }
}