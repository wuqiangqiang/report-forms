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
		/** �����ݿ������������Դ��ȡ���� */
		new AsyncTask<Void, Void, Void>() {

			ProgressDialog dialog = null;

			protected void onPreExecute() {
				dialog = new ProgressDialog(MainActivity.this);
				dialog.setMessage("��ȡ������... ");
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
		/** ����չ���ڱ������ */
		RowField[] rowFields = new RowField[] { new RowField("Ʒ��",true), new RowField("����",true),
		/* ����true�����Ƿ���Ҫ�ϼ���������������������� */
		/* new RowField("���", true), new RowField("�Ա�", true) */};
		/** ����չ���ڱ�ͷ���Ϸ� */
		ColumnField[] columnFields = new ColumnField[] { new ColumnField("����",true), new ColumnField("����װ",true), new ColumnField("���",true) /**/};
		try {
			columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<һ<��<��<��"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("����",DataType.INTEGER));
		dataFieldList.add(new DataField("����","����"));
		dataFieldList.add(new DataField("�Ѷ���",DataType.INTEGER));
		dataFieldList.add(new DataField("�ܿ���",DataType.INTEGER));
		dataFieldList.add(new DataField("�Ѷ����","�Ѷ���","�ܿ���",true));
		/** �ڿ�ʼ����ǰ���Ե������ȶԻ��� */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("��ʼ����");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// ��Ҫ�кϼ�
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// ��Ҫ�кϼ�
				return true;
			}
		});
	}

	public void demo2(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** ����չ���ڱ������ */
		RowField[] rowFields = new RowField[] {
		/* new RowField("Ʒ��", true), new RowField("����", true), */
		/* ����true�����Ƿ���Ҫ�ϼ���������������������� */
		/* new RowField("���", true), new RowField("�Ա�", true) */};
		/** ����չ���ڱ�ͷ���Ϸ� */
		ColumnField[] columnFields = new ColumnField[] { new ColumnField("����",true), new ColumnField("����װ",true), new ColumnField("���",true) /**/};
		try {
			columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<һ<��<��<��"));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("����",DataType.INTEGER));
		dataFieldList.add(new DataField("����","����"));
		// dataFieldList.add(new DataField("�Ѷ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�ܿ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�Ѷ����", "�Ѷ���","�ܿ���",true));
		/** �ڿ�ʼ����ǰ���Ե������ȶԻ��� */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("��ʼ����");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// ��Ҫ�кϼ�
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// ��Ҫ�кϼ�
				return true;
			}
		});
	}

	public void demo3(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** ����չ���ڱ������ */
		RowField[] rowFields = new RowField[] { new RowField("Ʒ��",true), new RowField("����",true),
		/* ����true�����Ƿ���Ҫ�ϼ���������������������� */
		new RowField("���",true) /* , new RowField("�Ա�", true) */};
		/** ����չ���ڱ�ͷ���Ϸ� */
		ColumnField[] columnFields = new ColumnField[] {/* new ColumnField("����", true), new ColumnField("����װ", true) ,
														 * new ColumnField("���", true) */};
		// try {
		// columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<һ<��<��<��"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("����",DataType.INTEGER));
		dataFieldList.add(new DataField("����","����"));
		// dataFieldList.add(new DataField("�Ѷ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�ܿ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�Ѷ����", "�Ѷ���","�ܿ���",true));
		/** �ڿ�ʼ����ǰ���Ե������ȶԻ��� */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("��ʼ����");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// ��Ҫ�кϼ�
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// ��Ҫ�кϼ�
				return true;
			}
		});
	}

	public void demo4(View v) {
		hidePanel();
		reportViewContainer.removeAllViews();
		/** ����չ���ڱ������ */
		RowField[] rowFields = new RowField[] {
		/* new RowField("Ʒ��", true), new RowField("����", true), */
		/* ����true�����Ƿ���Ҫ�ϼ���������������������� */
		/* new RowField("���", true), new RowField("�Ա�", true) */};
		/** ����չ���ڱ�ͷ���Ϸ� */
		ColumnField[] columnFields = new ColumnField[] {/* new ColumnField("����", true), new ColumnField("����װ", true) ,
														 * new ColumnField("���", true) */};
		// try {
		// columnFields[0].setRuleBasedCollator(new RuleBasedCollator("<һ<��<��<��"));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		ArrayList<DataField> dataFieldList = new ArrayList<DataField>();
		dataFieldList.add(new DataField("����",DataType.INTEGER));
		dataFieldList.add(new DataField("����","����"));
		// dataFieldList.add(new DataField("�Ѷ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�ܿ���", DataType.INTEGER));
		// dataFieldList.add(new DataField("�Ѷ����", "�Ѷ���","�ܿ���",true));
		/** �ڿ�ʼ����ǰ���Ե������ȶԻ��� */
		final ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage("loading...");
		dialog.setCancelable(false);
		dialog.show();
		ReportUtil.getReportTable(this,mDataList,rowFields,columnFields,dataFieldList,new OnReportBuildListener() {

			@Override
			public void onReportLayout(View reportTableHeader, View reportTableBody) {
				dialog.setMessage("��ʼ����");
				reportViewContainer.addView(reportTableHeader);
				reportViewContainer.addView(reportTableBody);
				dialog.dismiss();
			}

			@Override
			public boolean needRowTotal() {
				// ��Ҫ�кϼ�
				return true;
			}

			@Override
			public boolean needColumnTotal() {
				// ��Ҫ�кϼ�
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
		// �������򣺲��Ρ�����װ
		// ����Ʒ��
		// ����������
		// �����У�ֻ���������ֶξͺã���������������������Ǽ���
		ArrayList<IDataRow> datalist = new ArrayList<IDataRow>();
		for (int i = 0; i < 1000; i++) {
			final int k = i;
			IDataRow dataRow = new AbsDataRow() {

				/** ����װ */
				String dressClass = (k % 2) == 0 ? "��װ" : "��װ";
				String wave = (k % 3) == 0 ? "��һ��" : "�ڶ���";
				String brand = (1000 % (k + 1)) < 5 ? "�Ϳ�" : "������";
				String salesCount = String.valueOf(new Random().nextInt(100));
				String type = k % 5 == 0 ? "С��" : "����";
				String sex = new Random().nextInt(100) < 50 ? "Ů" : "��";
				String season = k % 3 == 0 ? "�ļ�" : "����";
				String totalStyle = String.valueOf(new Random().nextInt(100));
				String orderedStyle = "" + (int) Math.rint(Integer.parseInt(totalStyle) * 0.3);

				@Override
				public String getValue(String columnName) {
					if ("����װ".equals(columnName)) {
						return dressClass;
					}
					else if ("����".equals(columnName)) {
						return wave;
					}
					else if ("Ʒ��".equals(columnName)) {
						return brand;
					}
					else if ("����".equals(columnName)) {
						return salesCount;
					}
					else if ("���".equals(columnName)) {
						return type;
					}
					else if ("�Ա�".equals(columnName)) {
						return sex;
					}
					else if ("����".equals(columnName)) {
						return season;
					}
					else if ("�Ѷ���".equals(columnName)) {
						return orderedStyle;
					}
					else if ("�ܿ���".equals(columnName)) {
						return totalStyle;
					}
					else {
						throw new IllegalArgumentException("no such column");
					}
				}
			};
			datalist.add(dataRow);
		}
		// IDataRow myData0 = new MyData("��װ", "��һ��", "�Ϳ�", "10", "��Ӱ", "��", "�ļ�");
		// IDataRow myData1 = new MyData("��װ", "�ڶ���", "������", "20", "��Ӱ", "��", "�ļ�");
		// IDataRow myData2 = new MyData("��װ", "��һ��", "�Ϳ�", "30", "��Ӱ", "��", "�ļ�");
		// IDataRow myData3 = new MyData("��װ", "��һ��", "�Ϳ�", "40", "��Ӱ", "Ů", "�ļ�");
		// IDataRow myData4 = new MyData("��װ", "��һ��", "�Ϳ�", "50", "��Ӱ", "��", "�ļ�");
		// IDataRow myData5 = new MyData("��װ", "�ڶ���", "�Ϳ�", "60", "��Ӱ", "��", "�ļ�");
		// IDataRow myData6 = new MyData("��װ", "�ڶ���", "�Ϳ�", "70", "����", "��", "�ļ�");
		// IDataRow myData7 = new MyData("��װ", "�ڶ���", "������", "80", "��Ӱ", "Ů", "�ļ�");
		// IDataRow myData8 = new MyData("��װ", "�ڶ���", "�Ϳ�", "90", "��Ӱ", "��", "�ļ�");
		// IDataRow myData9 = new MyData("��װ", "�ڶ���", "�Ϳ�", "100", "��Ӱ", "��", "�ļ�");
		// IDataRow myData10 = new MyData("��װ", "��һ��", "�Ϳ�", "10", "��Ӱ", "��", "�ļ�");
		// IDataRow myData11 = new MyData("��װ", "��һ��", "������", "20", "����", "��", "�ļ�");
		// IDataRow myData12 = new MyData("��װ", "��һ��", "�Ϳ�", "30", "����", "��", "�ļ�");
		// IDataRow myData13 = new MyData("��װ", "��һ��", "�Ϳ�", "40", "����", "Ů", "�ļ�");
		// IDataRow myData14 = new MyData("��װ", "��һ��", "�Ϳ�", "50", "����", "��", "�ļ�");
		// IDataRow myData15 = new MyData("��װ", "�ڶ���", "�Ϳ�", "60", "��Ӱ", "��", "�ļ�");
		// IDataRow myData16 = new MyData("��װ", "�ڶ���", "�Ϳ�", "70", "��Ӱ", "��", "�ļ�");
		// IDataRow myData17 = new MyData("��װ", "�ڶ���", "������", "80", "��Ӱ", "Ů", "�ļ�");
		// IDataRow myData18 = new MyData("��װ", "�ڶ���", "�Ϳ�", "90", "��Ӱ", "��", "�ļ�");
		// IDataRow myData19 = new MyData("��װ", "�ڶ���", "�Ϳ�", "100", "��Ӱ", "��", "�ļ�");
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
