package com.neekoentertainment.theguardianreader;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.neekoentertainment.theguardianreader.model.Article;
import com.neekoentertainment.theguardianreader.utils.BitmapTools;

/**
 * Created by Nicolas on 7/30/2016.
 */
public class ArticleDetailsFragment extends Fragment {

    private TextView mArticleHeadline;
    private ImageView mArticleThumbnail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_detail, container, false);
        mArticleHeadline = (TextView) rootView.findViewById(R.id.article_detail_headline);
        mArticleThumbnail = (ImageView) rootView.findViewById(R.id.article_detail_thumbnail);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Article article = getArguments().getParcelable(ArticlesListFragment.FRAGMENT_ARGUMENT_LIST);
        if (article != null) {
            mArticleHeadline.setText(article.getHeadline());
            BitmapTools.loadBitmapIntoImageView(getActivity(), article.getThumbnail(), mArticleThumbnail);
        }
    }
}
