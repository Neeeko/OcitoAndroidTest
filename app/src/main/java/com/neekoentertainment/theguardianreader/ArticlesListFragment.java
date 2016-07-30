package com.neekoentertainment.theguardianreader;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.neekoentertainment.theguardianreader.model.Article;
import com.neekoentertainment.theguardianreader.utils.BitmapTools;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Nicolas on 7/30/2016.
 */
public class ArticlesListFragment extends ListFragment implements AdapterView.OnItemClickListener {

    public static final String URL = "http://public.ocito.com/m/test/guardian.html";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String FRAGMENT_ARGUMENT_LIST = "fragment_argument_list";
    public static final String TAG_FRAGMENT = "detail_fragment";
    private ProgressBar mProgressBar;
    private List<Article> mArticlesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_list, container, false);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrieveArticles();
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mArticlesList != null && mArticlesList.get(position) != null) {
            Fragment articleDetailsFragment = new ArticleDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable(FRAGMENT_ARGUMENT_LIST, mArticlesList.get(position));
            articleDetailsFragment.setArguments(bundle);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, articleDetailsFragment, TAG_FRAGMENT);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    private void retrieveArticles() {
        // Retrieves the JSON from the URL and populate a List<Article>
        Ion.with(this)
                .load(URL)
                .as(new TypeToken<List<Article>>() {})
                .setCallback(new FutureCallback<List<Article>>() {
                    @Override
                    public void onCompleted(Exception e, List<Article> result) {
                        mProgressBar.setVisibility(View.GONE);
                        if (result != null && result.size() > 0) {
                            mArticlesList = result;
                            getListView().setAdapter(new ArticlesAdapter(result));
                        }
                    }
                });
    }

    public class ArticlesAdapter extends BaseAdapter {

        private List<Article> mArticlesList;
        private LayoutInflater mLayoutInflater;

        ArticlesAdapter(List<Article> articlesList) {
            mArticlesList = articlesList;
            mLayoutInflater = LayoutInflater.from(getActivity());
        }

        @Override
        public int getCount() {
            return mArticlesList.size();
        }

        @Override
        public Article getItem(int position) {
            return mArticlesList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.item_article, parent, false);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.articleHeadline.setText(mArticlesList.get(position).getHeadline());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.FRENCH);
            simpleDateFormat.format(mArticlesList.get(position).getWebPublicationDate());
            viewHolder.articleDate.setText(getString(R.string.formatted_date, simpleDateFormat.format(mArticlesList.get(position).getWebPublicationDate())));
            BitmapTools.loadBitmapIntoImageView(getActivity(), mArticlesList.get(position).getThumbnail(), viewHolder.articleThumbnail);
            return convertView;
        }

        public class ViewHolder {
            public TextView articleHeadline;
            public TextView articleDate;
            public ImageView articleThumbnail;

            public ViewHolder(View view) {
                articleHeadline = (TextView) view.findViewById(R.id.article_headline);
                articleDate = (TextView) view.findViewById(R.id.article_date);
                articleThumbnail = (ImageView) view.findViewById(R.id.article_thumbnail);
            }
        }
    }
}
