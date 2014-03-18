package com.gong.calculation;

import java.io.IOException;

import org.boris.expr.Expr;
import org.boris.expr.ExprEvaluatable;
import org.boris.expr.ExprException;
import org.boris.expr.parser.ExprParser;
import org.boris.expr.util.Exprs;
import org.boris.expr.util.SimpleEvaluationContext;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private int[] btn_num_ids = {
									R.id.btn_Num_0,
									R.id.btn_Num_1,
									R.id.btn_Num_2,
									R.id.btn_Num_3,
									R.id.btn_Num_4,
									R.id.btn_Num_5,
									R.id.btn_Num_6,
									R.id.btn_Num_7,
									R.id.btn_Num_8,
									R.id.btn_Num_9,
									R.id.btn_ope_add,
									R.id.btn_ope_minus,
									R.id.btn_ope_multi,
									R.id.btn_ope_div,
									R.id.btn_Num_dot,
									R.id.btn_Num_equal,
									R.id.btn_ope_clear,
									R.id.btn_leftBracket,
									R.id.btn_rightBracket,
									R.id.btn_ope_del};
	private static TextView editText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editText = (TextView) findViewById(R.id.detailBox);
		initNumBtn();
	}
	
	private void initNumBtn()
	{
		for(int i = 0;i<btn_num_ids.length;i++)
		{
			findViewById(btn_num_ids[i]).setOnClickListener(onClickListener);
		}
	}
	
	OnClickListener onClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.btn_Num_0:
			case R.id.btn_Num_1:
			case R.id.btn_Num_2:
			case R.id.btn_Num_3:
			case R.id.btn_Num_4:
			case R.id.btn_Num_5:
			case R.id.btn_Num_6:
			case R.id.btn_Num_7:
			case R.id.btn_Num_8:
			case R.id.btn_Num_9:
			case R.id.btn_Num_dot:
			case R.id.btn_leftBracket:
			case R.id.btn_rightBracket:
			{
				Button temp = (Button)v;
				if("0".equals(editText.getText()))
				{
					editText.setText(temp.getText());
				}
				else
				{
					editText.setText(editText.getText().toString()+temp.getText());
				}
			}
				break;
			case R.id.btn_ope_add:
			case R.id.btn_ope_minus:
			case R.id.btn_ope_multi:
			case R.id.btn_ope_div:
			{
				Button temp = (Button)v;
				editText.setText(editText.getText().toString()+temp.getText());
			}
			break;
			case R.id.btn_ope_clear:
				editText.setText("0");
				break;
			case R.id.btn_Num_equal:
				editText.setText(getResult());
				break;
			case R.id.btn_ope_del:
			{
				CharSequence temp = editText.getText();
				editText.setText(temp.length()==1?"0":temp.subSequence(0, temp.length()-1));
			}
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private String getResult()
	{
		SimpleEvaluationContext context = new SimpleEvaluationContext();
		String temp = editText.getText().toString();
		String expression = temp.replace("¡Á", "*").replace("¡Â", "/");
		Expr e;
		try {
			e = ExprParser.parse(expression);
			Exprs.toUpperCase(e);
	        if (e instanceof ExprEvaluatable) {
	            e = ((ExprEvaluatable) e).evaluate(context);
	        }
	        String result = e.toString();
	        if(result.endsWith(".0"))
	        	result = result.substring(0, result.length()-2);
			return result;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			return temp;
		} catch (ExprException e1) {
			// TODO Auto-generated catch block
			return temp;
		}
	}

}
