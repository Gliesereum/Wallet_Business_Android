package com.gliesereum.coupler_business.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.gliesereum.coupler_business.BuildConfig;
import com.gliesereum.coupler_business.R;
import com.gliesereum.coupler_business.data.json.notificatoin.RegistrationTokenDeleteResponse;
import com.gliesereum.coupler_business.data.network.APIClient;
import com.gliesereum.coupler_business.data.network.APIInterface;
import com.gliesereum.coupler_business.data.network.CustomCallback;
import com.gliesereum.coupler_business.ui.LoginActivity;
import com.labters.lottiealertdialoglibrary.ClickListener;
import com.labters.lottiealertdialoglibrary.DialogTypes;
import com.labters.lottiealertdialoglibrary.LottieAlertDialog;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Response;

import static com.gliesereum.coupler_business.util.Constants.ACCESS_TOKEN;
import static com.gliesereum.coupler_business.util.Constants.FIREBASE_TOKEN;
import static com.gliesereum.coupler_business.util.Constants.IS_LOGIN;
import static com.gliesereum.coupler_business.util.Constants.USER_AVATAR;
import static com.gliesereum.coupler_business.util.Constants.USER_NAME;
import static com.gliesereum.coupler_business.util.Constants.USER_SECOND_NAME;

public class Util {
    private Activity activity;
    private Toolbar toolbar;
    private int identifier;
    private APIInterface API;
    private CustomCallback customCallback;
    private LottieAlertDialog alertDialog;
    private Drawer result;


    public Util(Activity activity, Toolbar toolbar, int identifier) {
        this.activity = activity;
        this.toolbar = toolbar;
        this.identifier = identifier;
        FastSave.init(activity.getApplicationContext());
        API = APIClient.getClient().create(APIInterface.class);
        customCallback = new CustomCallback(activity.getApplicationContext(), activity);
    }

    public void addNavigation() {
        new DrawerBuilder().withActivity(activity).build();
        PrimaryDrawerItem myBusinesses = new PrimaryDrawerItem().withName("Мои бизнесы").withIdentifier(1).withTag("myBusinesses").withIcon(R.drawable.ic_outline_store_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem analytics = new SecondaryDrawerItem().withName("Аналитика").withIdentifier(2).withTag("analytics").withSelectable(false).withIcon(R.drawable.ic_outline_insert_chart_outlined_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem orders = new SecondaryDrawerItem().withName("Заказы").withIdentifier(3).withTag("orders").withSelectable(false).withIcon(R.drawable.ic_outline_monetization_on_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem settings = new SecondaryDrawerItem().withName("Настройки").withIdentifier(4).withTag("settings").withSelectable(false).withIcon(R.drawable.ic_outline_settings_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem help = new SecondaryDrawerItem().withName("Помощь").withIdentifier(5).withTag("help").withSelectable(false).withIcon(R.drawable.ic_outline_help_outline_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem profileItem = new SecondaryDrawerItem().withName("Мой Профиль").withIdentifier(6).withTag("profile").withSelectable(false).withIcon(R.drawable.ic_outline_account_circle_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem logoutItem = new SecondaryDrawerItem().withName("Выйти").withIdentifier(7).withSelectable(false).withTag("logout").withSelectable(false).withIcon(R.drawable.ic_outline_exit_to_app_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem loginItem = new SecondaryDrawerItem().withName("Вход").withIdentifier(8).withSelectable(false).withTag("login").withSelectable(false).withIcon(R.drawable.ic_outline_exit_to_app_24px).withIconTintingEnabled(true);
        SecondaryDrawerItem versionItem = new SecondaryDrawerItem().withName("v" + BuildConfig.VERSION_NAME + " beta").withIdentifier(9).withSelectable(false).withTag("version").withSelectable(false);

        if (!FastSave.getInstance().getBoolean(IS_LOGIN, false)) {
            myBusinesses.withEnabled(false);
            analytics.withEnabled(false);
            orders.withEnabled(false);
            settings.withEnabled(false);
            profileItem.withEnabled(false);
        }

        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Picasso.get().load(uri).placeholder(placeholder).transform(new CircleTransform()).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.get().cancelRequest(imageView);
            }
        });

        ProfileDrawerItem profileDrawerItem = new ProfileDrawerItem();
        profileDrawerItem.withName(FastSave.getInstance().getString(USER_NAME, "") + " " + FastSave.getInstance().getString(USER_SECOND_NAME, ""));
        if (FastSave.getInstance().getString(USER_AVATAR, "").equals("")) {
            profileDrawerItem.withIcon(activity.getResources().getDrawable(R.mipmap.ic_launcher_round));
        } else {
            profileDrawerItem.withIcon(FastSave.getInstance().getString(USER_AVATAR, ""));
        }

        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withTextColorRes(R.color.black)
                .withHeaderBackground(R.drawable.cover_karma_new)
                .withSelectionListEnabledForSingleProfile(false)
                .withCompactStyle(true)
                .addProfiles(profileDrawerItem)
                .build();

        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withActivity(activity);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withSelectedItem(identifier);
        drawerBuilder.addDrawerItems(
                myBusinesses,
                analytics,
                orders,
                settings,
                profileItem,
                help
        );
        result = drawerBuilder.build();
        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                switch (drawerItem.getTag().toString()) {
                    case "myBusinesses":
                        Toast.makeText(activity, "myBusinesses", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "analytics":
                        Toast.makeText(activity, "analytics", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "orders":
                        Toast.makeText(activity, "orders", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "settings":
                        Toast.makeText(activity, "settings", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "profileItem":
                        Toast.makeText(activity, "profileItem", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "help":
                        Toast.makeText(activity, "help", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "logout":
                        if (result.isDrawerOpen()) {
                            result.closeDrawer();
                        }
                        alertDialog = new LottieAlertDialog.Builder(activity, DialogTypes.TYPE_QUESTION)
                                .setTitle("Выход")
                                .setDescription("Вы действительно хотите выйти со своего профиля?")
                                .setPositiveText("Да")
                                .setNegativeText("Нет")
                                .setPositiveButtonColor(activity.getResources().getColor(R.color.md_red_A200))
                                .setPositiveListener(new ClickListener() {
                                    @Override
                                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                                        deleteRegistrationToken();
                                        FastSave.getInstance().deleteValue(IS_LOGIN);
                                        FastSave.getInstance().deleteValue(USER_NAME);
                                        FastSave.getInstance().deleteValue(USER_SECOND_NAME);
                                        activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                        activity.finish();
                                    }
                                })
                                .setNegativeListener(new ClickListener() {
                                    @Override
                                    public void onClick(@NotNull LottieAlertDialog lottieAlertDialog) {
                                        alertDialog.dismiss();
                                    }
                                })
                                .build();
                        alertDialog.setCancelable(false);
                        alertDialog.show();
                        break;
                    case "profile":
                        Toast.makeText(activity, "profile", Toast.LENGTH_SHORT).show();
                        result.closeDrawer();
                        break;
                    case "login":
                        activity.startActivity(new Intent(activity.getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        activity.finish();
                        break;
                }

                return true;
            }
        });


        result.addItem(new DividerDrawerItem());
        if (FastSave.getInstance().getBoolean(IS_LOGIN, false)) {
            result.addItem(logoutItem);
        } else {
            result.addItem(loginItem);
        }
        result.addItem(versionItem);
    }

    private void deleteRegistrationToken() {
        API.deleteRegistrationToken(FastSave.getInstance().getString(ACCESS_TOKEN, ""), FastSave.getInstance().getString(FIREBASE_TOKEN, ""))
                .enqueue(customCallback.getResponse(new CustomCallback.ResponseCallback<RegistrationTokenDeleteResponse>() {
                    @Override
                    public void onSuccessful(Call<RegistrationTokenDeleteResponse> call, Response<RegistrationTokenDeleteResponse> response) {

                    }

                    @Override
                    public void onEmpty(Call<RegistrationTokenDeleteResponse> call, Response<RegistrationTokenDeleteResponse> response) {

                    }
                }));

    }

    public static boolean checkExpirationToken(Long localDateTime) {
        if (localDateTime > System.currentTimeMillis()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getStringTime(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(new Date(millisecond));

    }

    public static String getStringDateTrue(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTimeInMillis(millisecond);
        return format.format(calendar.getTime());

    }

    public static String getStringDate(Long millisecond) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM");
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        return format.format(new Date(millisecond));
    }

//    public static boolean checkCarWashWorkTime(AllCarWashResponse carWash) {
//        String dayOfWeek = "";
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (day) {
//            case Calendar.MONDAY:
//                dayOfWeek = "MONDAY";
//                break;
//            case Calendar.TUESDAY:
//                dayOfWeek = "TUESDAY";
//                break;
//            case Calendar.WEDNESDAY:
//                dayOfWeek = "WEDNESDAY";
//                break;
//            case Calendar.THURSDAY:
//                dayOfWeek = "THURSDAY";
//                break;
//            case Calendar.FRIDAY:
//                dayOfWeek = "FRIDAY";
//                break;
//            case Calendar.SATURDAY:
//                dayOfWeek = "SATURDAY";
//                break;
//            case Calendar.SUNDAY:
//                dayOfWeek = "SUNDAY";
//                break;
//        }
//
//        for (int i = 0; i < carWash.getWorkTimes().size(); i++) {
//            if (carWash.getWorkTimes().get(i).getDayOfWeek().equals(dayOfWeek) && carWash.getWorkTimes().get(i).isIsWork()) {
//                if (carWash.getWorkTimes().get(i).getFrom() < (System.currentTimeMillis() + (carWash.getTimeZone() * 60000)) && carWash.getWorkTimes().get(i).getTo() > (System.currentTimeMillis() + (carWash.getTimeZone() * 60000))) {
//                    Log.d("test_log", "car wash work");
//                    return true;
//                }
//            }
//        }
//        Log.d("test_log", "car wash not work: ");
//        return false;
//    }

}
