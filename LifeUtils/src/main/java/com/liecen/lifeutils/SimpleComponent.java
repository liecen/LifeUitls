package com.liecen.lifeutils;
/*
 *  @author ： Life
 *  onCreate DateTime 2020/12/31  : 14:43
 *  in order to :
 */

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.binioter.guideview.Component;
import com.vastweb.mainappmvp.R;

public class SimpleComponent implements Component {

    @Override public android.view.View getView(LayoutInflater inflater) {

        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_frends, null);
        ll.setOnClickListener(new android.view.View.OnClickListener() {
            @Override public void onClick(android.view.View view) {
                Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
            }
        });
        return ll;
    }

    @Override public int getAnchor() {
        return Component.ANCHOR_BOTTOM;
    }

    @Override public int getFitPosition() {
        return Component.FIT_END;
    }

    @Override public int getXOffset() {
        return 0;
    }

    @Override public int getYOffset() {
        return 10;
    }
}
