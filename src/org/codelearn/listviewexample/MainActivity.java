package org.codelearn.listviewexample;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final ArrayList<String> list = new ArrayList<String>();
    	final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
    	
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(adapter);
		
	    new UpdateAdapter(adapter,list).execute();
	}
	
	private class UpdateAdapter extends AsyncTask<Void, Void, Void> {
		
		private ArrayAdapter adapter;
		private ArrayList<String> list;
		
		public UpdateAdapter(ArrayAdapter adapterRef, ArrayList<String> listRef){
			adapter = adapterRef;
			list = listRef;
		}

		@Override
		protected Void doInBackground(Void...voids) {	
			for (int i = 0; i < 10; ++i) {			
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    list.add("List item " + i);
			    publishProgress();
			    Log.d("codelearn","thread ran");
			}
			for (int i = 0; i < 10; ++i) {			
				 try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    list.remove(9 - i);
			    publishProgress();
			    Log.d("codelearn","thread ran");
			}
			return null;
		}
		
		
		protected void onProgressUpdate(Void...voids){
			adapter.notifyDataSetChanged();
		}
		
	}
}
