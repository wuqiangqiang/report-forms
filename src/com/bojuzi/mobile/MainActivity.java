package com.bojuzi.mobile;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.bojuzi.mobile.report.ReportUtil;
import com.bojuzi.mobile.report.ReportUtil.OnReportBuildListener;
import com.bojuzi.mobile.report.domain.ColumnField;
import com.bojuzi.mobile.report.domain.DataField;
import com.bojuzi.mobile.report.domain.DataType;
import com.bojuzi.mobile.report.domain.IDataRow;
import com.bojuzi.mobile.report.domain.IDataRow.AbsDataRow;
import com.bojuzi.mobile.report.domain.RowField;

/**
 * feng.deliang@qq.com qq
 */
public class MainActivity extends Activity {

	LinearLayout reportViewContainer, panel;
	private ArrayList<IDataRow> mDataList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);
		reportViewContainer = (LinearLayout) findViewById(R.id.ll_report);
		panel = (LinearLayout) findViewById(R.id.ll_panel);
		/** 从数据库或者其他数据源获取数据 */
		new AsyncTask<Void, Void, Void>() {

			ProgressDialog dialog = null;

			protected void onPreExecute() {
				dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("获取数据中... ");
				dialog.setCancelable(false);
				dialog.show();
			}

			protected void onPostExecute(Void result) {
				dialog.dismiss();
			}

			@Override
			protected Void doInBackground(Void... params) {
				mDataList = prepareData();
				return null;
			}
		}.execute();
	}

	public void showPanel(View v) {
		panel.setVisibility(View.VISIBLE);
	}

	private void hidePanel() {
		panel.setVisibility(View.GONE);
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		int menuID = item.getItemId();
		switch (menuID) {
		case R.id.demo1:
			demo1(null);
			break;
		case R.id.demo2:
			demo2(null);
			break;
		case R.id.demo3:
			demo3(null);
			break;
		case R.id.demo4:
			demo4(null);
			break;
		default:
			break;
		}
		return false;
	}

	public void demo1(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** 行域，展现在报表左侧 */
		RowField[] rowFields = new RowField[] { new RowField("品牌",true), new RowField("季节",true),
		/* 参数true代表是否需要合计项，可以无限添加行域和列域 */
		/* new RowField("类别", true), new RowField("性别", true) */};
		/** 列域，展现在表头正上方 */
		ColumnField[] columnFields = new ColumnField[] { new ColumnField("波段",true), new ColumnField("上下装",true), new ColumnField("类别",true) /**/};
		try {
			columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<一<二<三<四"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("销量",DataType.INTEGER));
		dataFieldList.add(new DataField("数比","销量"));
		dataFieldList.add(new DataField("已定款",DataType.INTEGER));
		dataFieldList.add(new DataField("总款数",DataType.INTEGER));
		dataFieldList.add(new DataField("已定款比","已定款","总款数",true));
		/** 在开始布局前可以弹出进度对话框 */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("开始布局");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// 需要行合计
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// 需要列合计
				return true;
			}
		});
	}

	public void demo2(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** 行域，展现在报表左侧 */
		RowField[] rowFields = new RowField[] {
		/* new RowField("品牌", true), new RowField("季节", true), */
		/* 参数true代表是否需要合计项，可以无限添加行域和列域 */
		/* new RowField("类别", true), new RowField("性别", true) */};
		/** 列域，展现在表头正上方 */
		ColumnField[] columnFields = new ColumnField[] { new ColumnField("波段",true), new ColumnField("上下装",true), new ColumnField("类别",true) /**/};
		try {
			columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<一<二<三<四"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("销量",DataType.INTEGER));
		dataFieldList.add(new DataField("数比","销量"));
		// dataFieldList.add(new DataField("已定款", DataType.INTEGER));
		// dataFieldList.add(new DataField("总款数", DataType.INTEGER));
		// dataFieldList.add(new DataField("已定款比", "已定款","总款数",true));
		/** 在开始布局前可以弹出进度对话框 */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("开始布局");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// 需要行合计
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// 需要列合计
				return true;
			}
		});
	}

	public void demo3(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** 行域，展现在报表左侧 */
		RowField[] rowFields = new RowField[] { new RowField("品牌",true), new RowField("季节",true),
		/* 参数true代表是否需要合计项，可以无限添加行域和列域 */
		new RowField("类别",true) /* , new RowField("性别", true) */};
		/** 列域，展现在表头正上方 */
		ColumnField[] columnFields = new ColumnField[] {/* new ColumnField("波段", true), new ColumnField("上下装", true) ,
														 * new ColumnField("类别", true) */};
		// try {
		// columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<一<二<三<四"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("销量",DataType.INTEGER));
		dataFieldList.add(new DataField("数比","销量"));
		// dataFieldList.add(new DataField("已定款", DataType.INTEGER));
		// dataFieldList.add(new DataField("总款数", DataType.INTEGER));
		// dataFieldList.add(new DataField("已定款比", "已定款","总款数",true));
		/** 在开始布局前可以弹出进度对话框 */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("开始布局");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// 需要行合计
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// 需要列合计
				return true;
			}
		});
	}

	public void demo4(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** 行域，展现在报表左侧 */
		RowField[] rowFields = new RowField[] {
		/* new RowField("品牌", true), new RowField("季节", true), */
		/* 参数true代表是否需要合计项，可以无限添加行域和列域 */
		/* new RowField("类别", true), new RowField("性别", true) */};
		/** 列域，展现在表头正上方 */
		ColumnField[] columnFields = new ColumnField[] {/* new ColumnField("波段", true), new ColumnField("上下装", true) ,
														 * new ColumnField("类别", true) */};
		// try {
		// columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<一<二<三<四"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("销量",DataType.INTEGER));
		dataFieldList.add(new DataField("数比","销量"));
		// dataFieldList.add(new DataField("已定款", DataType.INTEGER));
		// dataFieldList.add(new DataField("总款数", DataType.INTEGER));
		// dataFieldList.add(new DataField("已定款比", "已定款","总款数",true));
		/** 在开始布局前可以弹出进度对话框 */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("开始布局");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// 需要行合计
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// 需要列合计
				return true;
			}
		});
	}

	public void onPostCreate(Bundle bundle) {
		super.onPostCreate(bundle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main,menu);
		return true;
	}

	private static ArrayList<IDataRow> prepareData() {
		// 设置列域：波段、上下装
		// 行域：品牌
		// 数据域：销量
		// 数据行：只包含几个字段就好，就是行域列域和数据域那几个
		ArrayList<IDataRow> datalist = new ArrayList<IDataRow>();
		for (int i = 0; i < 1000; i++) {
			final int k = i;
			IDataRow dataRow = new AbsDataRow() {

				/** 上下装 */
				String dressClass = (k % 2) == 0 ? "上装" : "下装";
				String wave = (k % 3) == 0 ? "第一波" : "第二波";
				String brand = (1000 % (k + 1)) < 5 ? "耐克" : "阿迪王";
				String salesCount = String.valueOf(new Random().nextInt(100));
				String type = k % 5 == 0 ? "小类" : "大类";
				String sex = new Random().nextInt(100) < 50 ? "女" : "男";
				String season = k % 3 == 0 ? "夏季" : "春季";
				String totalStyle = String.valueOf(new Random().nextInt(100));
				String orderedStyle = "" + (int) Math.rint(Integer.parseInt(totalStyle) * 0.3);

				@Override
				public String getValue(String columnName) {
					if ("上下装".equals(columnName)) {
						return dressClass;
					}
					else if ("波段".equals(columnName)) {
						return wave;
					}
					else if ("品牌".equals(columnName)) {
						return brand;
					}
					else if ("销量".equals(columnName)) {
						return salesCount;
					}
					else if ("类别".equals(columnName)) {
						return type;
					}
					else if ("性别".equals(columnName)) {
						return sex;
					}
					else if ("季节".equals(columnName)) {
						return season;
					}
					else if ("已定款".equals(columnName)) {
						return orderedStyle;
					}
					else if ("总款数".equals(columnName)) {
						return totalStyle;
					}
					else {
						throw new IllegalArgumentException("no such column");
					}
				}
			};
			datalist.add(dataRow);
		}
		// IDataRow myData0 = new MyData("上装", "第一波", "耐克", "10", "火影", "男", "夏季");
		// IDataRow myData1 = new MyData("下装", "第二波", "阿迪王", "20", "火影", "男", "夏季");
		// IDataRow myData2 = new MyData("上装", "第一波", "耐克", "30", "火影", "男", "夏季");
		// IDataRow myData3 = new MyData("下装", "第一波", "耐克", "40", "火影", "女", "夏季");
		// IDataRow myData4 = new MyData("上装", "第一波", "耐克", "50", "火影", "男", "夏季");
		// IDataRow myData5 = new MyData("下装", "第二波", "耐克", "60", "火影", "男", "夏季");
		// IDataRow myData6 = new MyData("上装", "第二波", "耐克", "70", "海贼", "男", "夏季");
		// IDataRow myData7 = new MyData("下装", "第二波", "阿迪王", "80", "火影", "女", "夏季");
		// IDataRow myData8 = new MyData("上装", "第二波", "耐克", "90", "火影", "男", "夏季");
		// IDataRow myData9 = new MyData("下装", "第二波", "耐克", "100", "火影", "男", "夏季");
		// IDataRow myData10 = new MyData("上装", "第一波", "耐克", "10", "火影", "男", "夏季");
		// IDataRow myData11 = new MyData("下装", "第一波", "阿迪王", "20", "海贼", "男", "夏季");
		// IDataRow myData12 = new MyData("上装", "第一波", "耐克", "30", "海贼", "男", "夏季");
		// IDataRow myData13 = new MyData("下装", "第一波", "耐克", "40", "海贼", "女", "夏季");
		// IDataRow myData14 = new MyData("上装", "第一波", "耐克", "50", "海贼", "男", "夏季");
		// IDataRow myData15 = new MyData("下装", "第二波", "耐克", "60", "火影", "男", "夏季");
		// IDataRow myData16 = new MyData("上装", "第二波", "耐克", "70", "火影", "男", "夏季");
		// IDataRow myData17 = new MyData("下装", "第二波", "阿迪王", "80", "火影", "女", "夏季");
		// IDataRow myData18 = new MyData("上装", "第二波", "耐克", "90", "火影", "男", "夏季");
		// IDataRow myData19 = new MyData("下装", "第二波", "耐克", "100", "火影", "男", "夏季");
		// datalist.add(myData0);
		// datalist.add(myData1);
		// datalist.add(myData2);
		// datalist.add(myData3);
		// datalist.add(myData4);
		// datalist.add(myData5);
		// datalist.add(myData6);
		// datalist.add(myData7);
		// datalist.add(myData8);
		// datalist.add(myData9);
		// datalist.add(myData10);
		// datalist.add(myData11);
		// datalist.add(myData12);
		// datalist.add(myData13);
		// datalist.add(myData14);
		// datalist.add(myData15);
		// datalist.add(myData16);
		// datalist.add(myData17);
		// datalist.add(myData18);
		// datalist.add(myData19);
		return datalist;
	}
}
