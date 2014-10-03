package cz.skywall.lockableviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by lukas on 1.10.14.
 * LockableViewPager
 */
public class DummyFragment extends Fragment{

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getDataPos() {
        return dataPos;
    }

    public void setDataPos(int dataPos) {
        this.dataPos = dataPos;
    }

    public void setData(String data) {
        text = data;
        if(textView != null) {
            textView.setText(text);
        }
    }

    int pos = -1;
    int dataPos = -1;
    String text;

    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView.setText(text);
    }
}
