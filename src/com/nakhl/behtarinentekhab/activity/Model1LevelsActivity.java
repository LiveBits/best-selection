package com.nakhl.behtarinentekhab.activity;

import java.util.List;

import roboguice.inject.InjectView;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.inject.Inject;
import com.nakhl.behtarinentekhab.R;
import com.nakhl.behtarinentekhab.model.dao.JobDao;
import com.nakhl.behtarinentekhab.model.dao.LevelDao;
import com.nakhl.behtarinentekhab.model.entity.Exercise;
import com.nakhl.behtarinentekhab.model.entity.Job;
import com.nakhl.behtarinentekhab.model.entity.Level;
import com.nakhl.behtarinentekhab.model.entity.Model1;

/**
 * Model1Level activity.
 * 
 * @author Ahmad khalilfar
 * 
 */
public class Model1LevelsActivity extends FullScreenActivity {

	@InjectView(R.id.buttonExplain)
	private Button buttonExplain;

	@InjectView(R.id.buttonActivities)
	private Button buttonActivities;

	@InjectView(R.id.buttonExpriences)
	private Button buttonExpriences;

	@InjectView(R.id.buttonJobs)
	private Button buttonJobs;

	@InjectView(R.id.buttonSelfDet)
	private Button buttonSelfDet; // self determinate

	@InjectView(R.id.buttonResult)
	private Button buttonResult;

	@Inject
	private LevelDao levelDao;
	
	@Inject
	private JobDao jobDao;
	
	String message = "لطفا مراحل قبل را کامل کنید...";

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_model1_levels);
		initButtons();		
	}

	/* Menu */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		return super.onOptionsItemSelected(item);
	}

	/**
	 * Initialize buttons.
	 */
	private void initButtons() {

		buttonExplain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showExplainDialog();
			}
		});

		buttonActivities.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle b = new Bundle();
				b.putInt("type", 3);
				b.putInt("sub", 1);

				Intent intent = new Intent(getApplicationContext(),
						LevelsActivity.class);
				intent.putExtras(b);

				startActivity(intent);
			}
		});

		buttonExpriences.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level level;
				boolean isCompleted = true;
				for (int i = 0; i < 6; i++) {
					level = levelDao.queryForId(30 + i);
					if (level.getScore() == -1) {
						isCompleted = false;
						break;
					}
				}
				if (isCompleted) {

					Bundle b = new Bundle();
					b.putInt("type", 3);
					b.putInt("sub", 2);

					Intent intent = new Intent(getApplicationContext(),
							LevelsActivity.class);
					intent.putExtras(b);

					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), message,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		buttonJobs.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level level;
				boolean isCompleted = true;
				for (int i = 0; i < 12; i++) {
					level = levelDao.queryForId(30 + i);
					if (level.getScore() == -1) {
						isCompleted = false;
						break;
					}
				}
				if (isCompleted) {

					Bundle b = new Bundle();
					b.putInt("type", 3);
					b.putInt("sub", 3);

					Intent intent = new Intent(getApplicationContext(),
							LevelsActivity.class);
					intent.putExtras(b);

					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), message,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		buttonSelfDet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Level level;
				boolean isCompleted = true;
				for (int i = 0; i < 18; i++) {
					level = levelDao.queryForId(30 + i);
					if (level.getScore() == -1) {
						isCompleted = false;
						break;
					}
				}
				if (isCompleted) {

					Bundle b = new Bundle();
					b.putInt("type", 3);
					b.putInt("sub", 4);

					Intent intent = new Intent(getApplicationContext(),
							LevelsActivity.class);
					intent.putExtras(b);

					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), message,
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		buttonResult.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Level level;
				boolean isCompleted = true;
				for (int i = 0; i < 20; i++) {
					level = levelDao.queryForId(30 + i);
					if (level.getScore() == -1) {
						isCompleted = false;
						break;
					}
				}
				if (isCompleted) {

					Intent intent = new Intent(getApplicationContext(),
							Model1ResultActivity.class);
					startActivity(intent);

				} else {
					Toast.makeText(getApplicationContext(), message,
							Toast.LENGTH_SHORT).show();
				}

			}
		});

	}

	private void showExplainDialog() {

		/* Explain model 1 test */
		String ExplainText = "تست شخصیت شناسی هالند که توسط جان هالند تهیه شده بر اساس نظریه‌ی همخوانی شغل و شخصیت شکل گرفته و سال هاست در مدارس، دانشگاه‌ها، شرکت ها مورد استفاده قرار می‌گیرد. اعتبار آن نیز در کشورهای آمریکایی، اروپایی، آسیایی، استرالیایی و آفریقایی و همچنین ایران مورد تایید قرار گرفته است. این تست برای همه افراد به خصوص دانش آموزان بسیار مناسب و کاربردی می باشد.";
		ExplainText += "\n"
				+ "در تست شخصیت شناسی هالند افراد در 6 تیپ شخصیتی واقع گرا، متعارف و سازمان یافته ، جستجوگر، هنری، اجتماعی و متهور قرار می گیرند. هر فردی در هر یک از این تیپ ها امتیازی کسب می کند که برای تحلیل آزمون، سه تیپ شخصیتی که بیشترین امتیاز را به خود اختصاص داده،ملاک قرار می گیرد. هر تیپ شخصیتی ویژگی های خاصی را دارد و به دنبال آن مشاغل خاصی نیز برای آن پیشنهاد می گردد. افرادی که شغل خود را متناسب با تیپ شخصیتی خود انتخاب می کنند، از رضایت شغلی و احساس بهتری در کار خود برخوردار خواهند بود.";
		ExplainText += "\n" + "تست هالند بر اساس چهار فرضیه شکل گرفته است :";
		ExplainText += "\n"
				+ "1: افراد را می‌توان در یکی از شش تیپ، واقع‌گرا، معنوی، هنری، اجتماعی، متهور، و قراردادی طبقه‌بندی کرد.";
		ExplainText += "\n"
				+ "2: محیط‌ها (اعم از کاری و غیر کاری) را هم می‌توان در یکی از شش مدل واقع‌گرایانه، جستجوگرایانه، هنری، اجتماعی، متهورانه و قراردادی طبقه‌بندی نمود. هر محیطی تحت تسلط یک تیپ شخصیتی قرار دارد و هر محیط نمونه‌ای از وضع فیزیکی خاص است که دارای شرایط و امکان‌های مخصوص به خود است. مثلاً محیط‌های اجتماعی تحت تسلط افراد اجتماعی قرار دارند. وضعیت فیزیکی آن وجود افرادی است که در تعامل با یکدیگرند و شرایط و امکان‌های مخصوص به آن از طریق روابط بین فردی تحقق می‌پذیرد.";
		ExplainText += "\n"
				+ "3: افراد به دنبال محیط‌هایی هستند که بتوانند مهارت‌ها و توانایی‌های خود را به خوبی در آنها بکار بگیرند. نگرش‌ها و ارزش‌های خود را نشان دهند و نقش‌های متناسب با آن را به عهده گیرند. به عنوان مثال تیپ‌های اجتماعی به دنبال محیط‌های اجتماعی و تیپ‌های قراردادی به دنبال محیط‌های قراردادی هستند.";
		ExplainText += "\n"
				+ "4: رفتار هر فرد به وسیله‌ی تعامل بین شخصیت و محیط او تعیین می‌گردد. اگر تیپ شخصیتی یک فرد و الگوی محیطی او مشخص باشد، می‌توان به پیش‌بینی برخی از رفتارها و اعمال او پرداخت. این گونه نتایج می‌تواند در انتخاب رشته‌ی تحصیلی، انتخاب شغل و غیره مفید باشد.";

		ExplainText += "\n\n" + "بررسی انواع تیپ های شخصیتی";

		ExplainText += "\n\n" + "1.	 تیپ شخصیتی واقع گرا(Realistic) :";
		ExplainText += "\n\n"
				+ ">> افرادی خوددار، مصر، واقع بین، جدی در کار، انعطاف ناپذیر، مادی گرا، سر سخت و متواضع اند.";
		ExplainText += "\n" + ">> دارای توانایی بدنی و مکانیکی هستند.";
		ExplainText += "\n"
				+ ">> علاقمند به کار با اشیاء، ماشین ها ، ابزار، گیاهان و حیوانات هستند.";
		ExplainText += "\n"
				+ ">> به دنبال مزد و پاداش کار و دستاوردهای عینی هستند.";
		ExplainText += "\n" + ">> افرادی واقع گرا، محافظه کار و صریح هستند.";
		ExplainText += "\n" + ">> معمولا در ارتباطات اجتماعی ضعف دارند.";
		ExplainText += "\n"
				+ ">> به دنبال راه حل های عملی و بدنی برای حل مشکلات زندگی خود می گردند.";

		ExplainText += "\n\n" + "2.	تیپ شخصیتی متعارف و سازمان یافته(Conventional) :";
		ExplainText += "\n\n"
				+ ">> افرادی سازمان یافته، مرتب، واقع بین، پیگیر، انعطاف ناپذیر، کارآمد، وظیفه شناس، مطیع، فاقد تخیل، مدافع و صرفه جو هستند.";
		ExplainText += "\n" + ">> به فعالیت های جسمانی علاقه ای ندارند.";
		ExplainText += "\n" + ">> دوست دارند با داده ها کارکنند.";
		ExplainText += "\n" + ">> توانایی دفتری بالایی دارند.";
		ExplainText += "\n" + ">> به جزئیات امور توجه دارند.";
		ExplainText += "\n"
				+ ">> علاقمند به انجام کارهای استاندارد و معین هستند.";
		ExplainText += "\n" + ">> توانایی سازماندهی کارها را دارند.";
		ExplainText += "\n" + ">> به دنبال دستاوردهای مالی هستند.";
		ExplainText += "\n"
				+ ">> کسب قدرت در عرصه های اجتماعی، کاری و سیاسی برای آنها بسیار مهم است.";
		ExplainText += "\n"
				+ ">> در امور تجاری، اداری، مالی و تولیدی دارای توانایی تخصصی هستند.";
		ExplainText += "\n" + ">> در امور هنری ضعیف اند.";
		ExplainText += "\n"
				+ ">> از دید دیگران افرادی محتاط و محافظه کار به حساب می آیند.";
		ExplainText += "\n" + ">> از امور مبهم و پیچیده دوری می کنند.";
		ExplainText += "\n"
				+ ">> در برخورد با مشکلات زندگی به دنبال راه حل هایی تدوین شده می گردند.";

		ExplainText += "\n\n" + "3.	تیپ شخصیتی جستجوگر(Investigative) :";
		ExplainText += "\n\n"
				+ ">> افرادی خوددار، محتاط، تحلیل گر، نقاد، کنجکاو، مستقل، روشنفکر، درون نگر، بد بین، پیجیده، دقیق، منطقی، فروتن و کناره گیر هستند.";
		ExplainText += "\n" + ">> آنها طرفدار اصلاحات اساسی و بنیادین هستند.";
		ExplainText += "\n" + ">> آنها اهل تفکر، سازمان دهی و استدلال اند.";
		ExplainText += "\n" + ">> روابط اجتماعی قوی ندارند.";
		ExplainText += "\n" + ">> دوست دارند مشاهده کنند و یاد بگیرند.";
		ExplainText += "\n" + ">> عاشق تحقیق، تجزیه وتحلیل و حل مساله اند.";
		ExplainText += "\n" + ">> توجه ویژه ای به رشد، توسعه و کسب علم دارند.";
		ExplainText += "\n" + ">> از دید دیگران افرادی گوشه گیر و متفکر هستند.";
		ExplainText += "\n" + ">> در متقاعد کردن دیگران بسیار ضعیف اند.";
		ExplainText += "\n"
				+ ">> برای حل مشکلات زندگی خود، راه حل های فکری را می یابند.";

		ExplainText += "\n\n" + "4.	تیپ شخصیتی هنری(Artistic) : ";
		ExplainText += "\n\n"
				+ ">> افرادی احساسی، نامرتب، آرمان گرا، خیال پرداز، مستقل، شمی، درو نگر، مبتکر، ابرازگر، حساس، مبهم، غیر سنتی و دارای منش و روحیه بازی هستند.";
		ExplainText += "\n" + ">> در شناسایی و بیان خصوصیات خود مهارت دارند.";
		ExplainText += "\n"
				+ ">> کمتر به کنترل خود پرداخته و احساسات خود را به راحتی بیان  می کنند.";
		ExplainText += "\n" + ">> ارتباط خوبی با دیگران دارند.";
		ExplainText += "\n" + ">> اشخاصی با توانایی های هنری هستند.";
		ExplainText += "\n"
				+ ">> به انجام کارهای پیچیده، ابتکاری و هنری علاقه دارند.";
		ExplainText += "\n" + ">> افرادی نوآور و خلاق اند.";
		ExplainText += "\n"
				+ ">> علاقمند به کار در موقعیت های سازمان نیافته هستند.";
		ExplainText += "\n" + ">> دوست دارند چیزهای مختلف را تجربه کنند.";
		ExplainText += "\n" + ">> در مهارت های اداری و دفتری ضعیف اند.";
		ExplainText += "\n"
				+ ">> از دید دیگران سنت گریز، نامرتب و مبتکر هستند.";
		ExplainText += "\n"
				+ ">> از انجام کارهای تکراری و پیروی از قوانین موجود متنفرند.";
		ExplainText += "\n"
				+ ">> در حل مشکلات زندگی خود به راه حل های خلاقانه و گاه هنری روی می آورند.";

		ExplainText += "\n\n" + "5.	تیپ شخصیتی اجتماعی(Social) :";
		ExplainText += "\n\n"
				+ ">>  افرادی مسئول، صمیمی، آرمان گرا، سخاوتمند، با کیاست، مهربان، همدل، خوش مشرب، مبادی آداب، معاشرتی، صبور، دارای روحیه همکاری، متقاعد کننده، مشوق، فهمیده و اهل رفاقت و دوستی اند.";
		ExplainText += "\n"
				+ ">> دوست دارند با مردم کار کنند تا آنها را آگاه و مطلع نمایند.";
		ExplainText += "\n" + ">> دارای مهارت های کلامی هستند.";
		ExplainText += "\n"
				+ ">> علاقمند به امدادگری، تعلیم ، درمان، مشاوره و خدمت به دیگران با تکیه بر روابط میان فردی هستند.";
		ExplainText += "\n" + ">> برای بهبود امور دیگران تلاش فراوان می کنند.";
		ExplainText += "\n" + ">> عاشق خدمات اجتماعی اند.";
		ExplainText += "\n"
				+ ">> از دید دیگران افرادی مردم دوست، خوش خلق و برون گرا هستند.";
		ExplainText += "\n" + ">> به فعالیت های مکانیکی و فنی علاقه ای ندارند.";
		ExplainText += "\n"
				+ ">> برای حل مشکلات زندگی خود راه حل های احساسی(درک احساسات خود و دیگری) را انتخاب می کنند.";

		ExplainText += "\n\n" + "6.	تیپ شخصیتی متهور(Enterprising) :";
		ExplainText += "\n\n"
				+ ">> افرادی ماجراجو، بلندپرواز، مطمئن به خود، برون نگر، هیجان طلب، معاشرتی، خوش بین، با انرژی، رهبر، مایل به جمع آوری دارایی، آگاه به منابع مختلف، باشوق، ریسک کننده و مقتدرند.";
		ExplainText += "\n"
				+ ">> دوست دارند با مردم کارکرده و آنها را برای رسیدن به اهداف سازمانی یا درامد اقتصادی رهبری و مدیریت کنند.";
		ExplainText += "\n" + ">> در متقاعد کردن دیگران توانمند هستند.";
		ExplainText += "\n"
				+ ">> به دنبال کسب دستاوردهای مالی و موقعیت مناسب اجتماعی اند.";
		ExplainText += "\n" + ">> توانایی خوبی در بازاریابی و فروش دارند.";
		ExplainText += "\n" + ">> به علوم پایه و امور پژوهشی علاقه ندارند.";
		ExplainText += "\n" + ">> از دید دیگران افرادی اجتماعی و پرانرژی اند.";
		ExplainText += "\n"
				+ ">> در برخورد با مشکلات زندگی خود، راه حل های ریسک پذیر را انتخاب می کنند.";

		ExplainText += "\n\n"
				+ " یکی از مهم ترین کاربردهایتست هالند که برای مشاوران و عموم افراد می‌تواند فوق‌العاده حائز اهمیت باشد، طبقه‌بندی مشاغل است. به کمک این طبقه‌بندی مشاور و یا هر فردی می تواند نوع شخصیت را با کمک تست هالند شناخته، نوع محیط شغلی مناسب را نیز مورد شناسایی قرار ‌دهد و سپس ارتباط و سازگاری بین آن دو ایجاد کند.نظریه هالند کاربردهای زیاد دیگری نیز دارد که عبارتند از :";
		ExplainText += "\n"
				+ "1: ارائه اطلاعات شغلی و حرفه‌ای به ساده‌ترین صورت ممکن بطوریکه عملاً قابل استفاده و کاربرد باشد.";
		ExplainText += "\n"
				+ "2: توصیف جریان انتخاب شغل و ارتباط آن با نوع شخصیت فرد.";
		ExplainText += "\n"
				+ "3: فراهم آوردن امکانات و فرصت‌هایی برای خودشناسی و شناخت نیازهای محیطی.";

		
		/* Structure of model 1 test */
		
		String StructureText = "بخش های مختلف آزمون شامل مواد زیر است:";
		StructureText += "\n" + "1: فعالیت ها";
		StructureText += "\n" + "2: تجربه ها";
		StructureText += "\n" + "3: مشاغل";
		StructureText += "\n" + "4: خود سنجی ها";
		
		StructureText += "\n\n" + ">> فعالیت ها";
		StructureText += "\n" + "این بخش از شش قسمت شامل تیپ های شخصیتی واقع گرا ، جستجو گر ، هنری ، اجتماعی ، متهورانه و قراردادی تشکیل شده است و هر قسمت دارای 11 پرسش می باشد که علاقه شما را نسبت به فعالیت های مختلف می سنجد. اگر شما به فعالیتی علاقه دارید هر چند که تاکنون آن را انجام نداده باشید می توانید جواب بله را انتخاب کنید و در صورت علاقه مند نبودن جواب خیر را انتخاب کنید.";
		
		StructureText += "\n\n" + ">> تجربه ها ";
		StructureText += "\n" + "این بخش نیز از شش قسمت تشکیل شده(مانند قسمت فعالیت ها) و هر قسمت شامل 11 سوال می باشدکه تجربه کسب شده شما را می سنجد. در این قسمت اگر شما موضوعی را تجربه کرده باشید هر چند به صورت محدود و جزیی جواب بله و اگر تجربه نکرده باشید حتی اگر به آن علاقه داشته باشید جواب خیر را انتخاب کنید.";
		
		StructureText += "\n\n" + ">> مشاغل";
		StructureText += "\n" + "این بخش نیز مانند بخش های قبلی از شش قسمت تشکیل شده و در هر قسمت شامل 14 شغل مطرح شده است بدین معنی که اگر شما به شغلی علاقه دارید حتی اگر قصد انتخاب آنرا برای آینده ندارید جواب بله و اگر هیچ علاقه ای به آن دارید جواب خیر را انتخاب کنید.";
		
		StructureText += "\n\n" + ">> خودسنجی ها";
		StructureText += "\n" + "خودسنجی ها به دو بخش زیر تقسیم می شوند:";
		StructureText += "\n" + "الف: خودسنجی 1، در خصوص استعداد ها و توانایی ها";
		StructureText += "\n" + "ب: خودسنجی 2، در خصوص مهارت ها";
		
		StructureText += "\n\n" + ">>> خودسنجی 1";
		StructureText += "\n" + "در این بخش از افراد خواسته می شود تا خود را در مقایسه با همسالان خود ، نه افراد متخصص و بزرگتر یا کوچکتراز خود از نظر استعداد ها و توانایی ها ، درجه بندی و نمره گذاری کنند. حداقل نمره 1 و حداکثر 7 است. به طور مثال اگر شما استعداد و توانایی هنری خود را در مقایسه با همسال هایتان بسیار بالا می بینید نمره 7 و چنانچه بسیار پایین می بینید نمره 1 برای خود در نظر بگیرید. باید توجه داشت نمره هر استعداد و توانایی نباید با سایر استعداد ها و توانایی ها یکسان و یا تکراری باشد.";
		
		StructureText += "\n\n" + ">>> خودسنجی 2";
		StructureText += "\n" + "در این بخش از افراد خواسته می شود تا خود را در مقایسه با همسالان خود ، نه افراد متخصص و بزرگتر یا کوچکتراز خود از نظر مهارت ها ، درجه بندی و نمره گذاری کنند. حداقل نمره 1 و حداکثر 7 است. به طور مثال اگر شما مهارت موسیقی خود را در مقایسه با همسال هایتان بسیار بالا می بینید نمره 7 و چنانچه بسیار پایین می بینید نمره 1 برای خود در نظر بگیرید. باید توجه داشت نمره هر مهارت نباید با سایر مهارت ها یکسان و یا تکراری باشد.";
		
		StructureText += "\n\n" + "در انتها یک یا چند کد اختصاری سه حرفی (ترتیب اولویت از راست به چپ) که بیانگر تیپ شخصیتی شما هست نمایش داده می شود که با استفاده از این کد می توانید شغل های مناسب خود را به ترتیب اولویت از بین 500 شغل از متداول ترین مشاغل مشاهده نمایید.";
		
		StructureText += "\n\n" + "حروف کد:";
		StructureText += "\n" + " و (واقع گرا) شامل مشاغل مهارتی ، فنی و بعضی از مشاغل خدماتی";
		StructureText += "\n" + "ج (جستجو گر) شامل مشاغل علمی و بعضی از مشاغل فنی";
		StructureText += "\n" + "ه (هنری) شامل مشاغل هنری ، موسیقی و ادبیات";
		StructureText += "\n" + "الف (اجتماعی) شامل مشاغل مربوط به تعلیم و تربیت و رفاه اجتماعی";
		StructureText += "\n" + "م (متهورانه) شامل مشاغل مدیریت و فروشندگی";
		StructureText += "\n" + "ق (قرار دادی) شامل مشاغل اداری و منشیگری";
		
		StructureText += "\n\n" + "كدهاي 3 حرفي ، مشاغل را توصيف مي كنند . براي مثال معناي كد م الف ق اين است كه فرد بيش از همه به مشاغل متهورانه ، تا حدي كمتر به مشاغل اجتماعي و كمتر از آن به مشاغل قراردادي علاقه مند مي باشد.";		
		
		// custom dialog
		final Dialog dialog = new ExplainTabDialog(Model1LevelsActivity.this);
		//dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//dialog.setContentView(R.layout.activity_explain_swipe);
		// dialog.setTitle("Title...");

		//explain
		TextView tvExplain = (TextView) dialog
				.findViewById(R.id.textViewExplain);
		tvExplain.setText(ExplainText);
		tvExplain.setMovementMethod(new ScrollingMovementMethod());

		//structure
		TextView tvStructure = (TextView) dialog
				.findViewById(R.id.textViewStructure);
		tvStructure.setText(StructureText);
		tvStructure.setMovementMethod(new ScrollingMovementMethod());
		
		Button dialogButton = (Button) dialog.findViewById(R.id.buttonOk);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.setCanceledOnTouchOutside(false);

		dialog.show();

	}

	@Override
	public void onBackPressed() {

		Intent intent = new Intent(getApplicationContext(),
				JobsCategoriesActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);

	}

}
