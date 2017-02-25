package com.atguigu.shoppingmall_1020.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.atguigu.shoppingmall_1020.home.bean.HomeBean;
import com.atguigu.shoppingmall_1020.utils.Constants;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2017/2/25 09:09
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class ViewPagerAdapter extends PagerAdapter {
    private final Context mContext;
    private final List<HomeBean.ResultEntity.ActInfoEntity> datas;

    public ViewPagerAdapter(Context mContext, List<HomeBean.ResultEntity.ActInfoEntity> act_info) {
        this.mContext = mContext;
        this.datas = act_info;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+datas.get(position).getIcon_url()).into(imageView);
        //添加到ViewPager容器中
        container.addView(imageView);

        //设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "position=="+position, Toast.LENGTH_SHORT).show();
                if(listener != null){
                    listener.onItemClick(v,position);
                }
            }
        });

        return imageView;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    /**
     * 点击item的接口
     */
    public interface OnItemClickListener{
        public void onItemClick(View v,int position);
    }
    private OnItemClickListener listener;

    /**
     * 设置item的点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
