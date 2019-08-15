package com.example.testmap;

import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;


class distance_sort implements Comparable<distance_sort>
{
	int a;
	int b;
	int diff;
	distance_sort(int a,int b,int d,int f)
	{
		this.a=a;
		this.b=b;
		int c=(d-a)>0?(d-a):convert(d-a);
		int k=(f-b)>0?(f-b):convert(f-b);
		this.diff=(c+k)/2;
	}
	@Override
	public int compareTo(distance_sort Q) {
		return (this.diff - Q.diff);
		// TODO Auto-generated method stub
		
	}
	int convert(int m)
	{
		return m*(-1);
	}
}
public class AndroidPaint extends Activity {
	String p;
	int q=0;
	distance_sort skt[] = new distance_sort[19];
	int array[]={440,670,430,410,590,800,780,630,170,230,280,100,160,620,240,600,760,200,590,220};
	int arrrr[]={700,610,470,940,860,850,720,400,820,480,640,700,970,1080,280,260,500,1110,1280,1260};
	/*int array[]={440,280,430,410,590,670,100,780,170,230,630,800,760,160,620,600,240,200,590,220};
	int arrrr[]={700,640,470,940,860,610,700,720,820,480,400,850,500,970,1080,260,280,1110,1280,1260};*/
	ImageButton img;
	ImageButton mButton;
	String st[]={"WellFood Corner","MFC","KFC","Sadia Kitchen","kkk","LLL","PPP","QQQ","AAA","BBB","CCC","DDD","ZZZ","SSS","RRR","MMM","NNN","TTT","HHH","JJJ"};
	//private static final String TAG = null;
	//ImageView image;
    /** Called when the activity is first created. */
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_android_paint);
	        mButton =(ImageButton) findViewById(R.id.btn1);
	        Intent in = getIntent();  
	        ArrayList<String> list = in.getStringArrayListExtra("places");
	        q=in.getIntExtra("call", 0);
	       // p=in.getExtra("call");
	       // int d=0;
	      // p=in.getStringExtra("call");
	     /* for(int i=0;i<19;i++)
	       {
	    	   if(list.get(i).contains(p))
	    	   {
	    		   d=i+1;
	    		   
	    	   }
	       }*/
	       // TextView text = new TextView(this);
	        //text.setText("Hello World, Android - mkyong.com");
	        //setContentView(text);
	        //Intent intent=getIntent();
	        
	        
	       // image=(ImageView)findViewById(R.id.imageView);
	       createBitMap(list);
	       int i=q+1;//roar(list,p);
	       double k=1;//2.1212;
	    
	    double    ip=(double)(array[i]-array[0]);
	    double   kp=(double)(arrrr[i]-arrrr[0])/k;
	   
	       AnimatorPath path = new AnimatorPath();
	        path.moveTo(0, 0);
	        path.lineTo((float)ip, (float)kp);
	      
	       // path.lineTo(array[0]+ip, arrrr[0]+kp);
	       // path.lineTo(array[0]+ip, arrrr[0]+2*kp);
	        //path.lineTo(array[0]+ip, arrrr[0]+3*kp);
	       //path.lineTo(array[i]/2, arrrr[i]/);
	      final ObjectAnimator anim = ObjectAnimator.ofObject(this, "buttonLoc", 
	                new PathEvaluator(), path.getPoints().toArray());
	        anim.setDuration(10000);
	        //anim.start();
	        mButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                anim.start();
	            }
	        });
	    }

	    private void createBitMap( ArrayList<String> list) {
	    	
	    
	    //	int array[]={440,600,440,440,580,800,780,600,240,230,280,100,260,620};
	    	//int arrrr[]={700,640,500,900,850,850,720,480,820,480,640,700,1000,1080};
	    	//String st[]={"WellFood Corner","MFC","KFC","Sadia Kitchen","kkk","LLL","PPP","QQQ","AAA","BBB","CCC","DDD","ZZZ","SSS"};
	    	BitmapFactory.Options myOptions = new BitmapFactory.Options();
	      myOptions.inDither = true;
	        myOptions.inScaled = false;
	        myOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;// important
	        myOptions.inPurgeable = true;
	       myOptions.outHeight = 2;
	       myOptions.outWidth = 2;
	        myOptions.inSampleSize = 2;
	        for(int i=1;i<20;i++)
	        {
	        	int p,q;
	        	p=array[i];
	        	q=arrrr[i];
	            
	        	skt[i-1]=new distance_sort(p,q,440,700);
	        }
	      
	        
	        
	        Arrays.sort(skt);
	      for(int i=1;i<=19;i++)
	        {
	        	array[i]=skt[i-1].a;
	        	arrrr[i]=skt[i-1].b;
	        }
	        for(int i=0;i<20;i++)
	        	array[i]=array[i]/2;
	        for(int i=0;i<20;i++)
	        	arrrr[i]=arrrr[i]/2;

	        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lock,myOptions);
	        Paint paint = new Paint();
	        paint.setAntiAlias(true);
	        paint.setColor(Color.BLUE);
	        //paint.setStyle(Paint.Style.STROKE);
	        //paint.setStrokeJoin(Paint.Join.ROUND);
	        //paint.setStrokeCap(Paint.Cap.ROUND);
	        //paint.setStrokeWidth(7f);

	        Bitmap workingBitmap = Bitmap.createBitmap(bitmap);
	        Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);

            
	        Canvas canvas = new Canvas(mutableBitmap);
	       // paint.setColor(Color.BLACK);
	        paint.setTextSize(13);
	       /* for(int i=1;i<20;i++)
	        {
	        	int p,q;
	        	p=array[i];
	        	q=arrrr[i];
	            
	        	skt[i-1]=new distance_sort(p,q,440,700);
	        }
	      
	        
	        
	        Arrays.sort(skt);
	      for(int i=1;i<=19;i++)
	        {
	        	array[i]=skt[i-1].a;
	        	arrrr[i]=skt[i-1].b;
	        }*/
	        canvas.drawCircle(array[0],arrrr[0], 10, paint);
	        canvas.drawText("own", array[0]+18, arrrr[0], paint);
	        for(int i=1;i<20;i++)
	        {
	        //canvas.drawCircle(skt[i].a, skt[i].b, 10, paint);
	        	 canvas.drawCircle(array[i],arrrr[i], 10, paint);
	        canvas.drawText(list.get(i-1), array[i]+18, arrrr[i], paint);
	       // canvas.drawText(list.get(i), skt[i].a+18, skt[i].b, paint);
	        //canvas.drawCircle(540, 620, 30, paint);
	        }
	       
	        //canvas.drawLine(array[0],arr[0] ,array[1] ,arr[1], paint); 
	        Paint mPaint = new Paint();
	        mPaint.setDither(true);
	        mPaint.setColor(Color.WHITE);
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeJoin(Paint.Join.ROUND);
	        mPaint.setStrokeCap(Paint.Cap.ROUND);
	        mPaint.setStrokeWidth(4);
/*Path path = new Path();
           // for(int i=1;i<=3;i++)
           // {
int lt,pt,ip,kp;
int i=11;
lt=array[i];
pt=arrrr[i];
ip=(lt-array[0])/4;
kp=(pt-arrrr[0])/4;
path.moveTo(array[0], arrrr[0]);
path.lineTo(array[0]+ip, arrrr[0]+kp);
path.lineTo(array[0]+ip, arrrr[0]+kp);
path.lineTo(array[0]+ip, arrrr[0]+kp);
path.lineTo(array[i], arrrr[i]);
canvas.drawPath(path,mPaint);*/
/*int i=2;
			path.moveTo(array[0], arrrr[0]);
			path.lineTo(array[0]-10, arrrr[0]-50);
			path.lineTo(array[0]-10, arrrr[0]-100);
			path.lineTo(array[0]-10, arrrr[0]-150);
			path.lineTo(array[0], arrrr[i]);
			canvas.drawPath(path,mPaint);*/
			
			
			
			
          //  }
	        //canvas.drawLine(440,520,540,620,paint);
	        ImageView imageView = (ImageView)findViewById(R.id.imageView1);
	        imageView.setAdjustViewBounds(true);
	        imageView.setImageBitmap(mutableBitmap);
	        
	    }
	    public void setButtonLoc(PathPoint newLoc) {
	        mButton.setTranslationX(newLoc.mX);
	        mButton.setTranslationY(newLoc.mY);
	    }
	    
	    public int roar(ArrayList<String> list,String name)
	    {
	    	try
	    	{
	    	for(int i=0;i<20;i++)
	    	{
	    		String k=list.get(i);
	    		if(k.contains(p))
		    	   {
		    		   return i+1;
		    		   
		    	   }
	    	}
	    	}
	    	catch(NullPointerException e)
	    	{
	    		
	    	}
	    	
	    	
	    	return 0;
	    }
}
