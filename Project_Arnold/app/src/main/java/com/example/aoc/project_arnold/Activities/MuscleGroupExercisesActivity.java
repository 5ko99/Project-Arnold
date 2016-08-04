package com.example.aoc.project_arnold.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aoc.project_arnold.Adapters.ListViewAdapter;
import com.example.aoc.project_arnold.R;

public class MuscleGroupExercisesActivity extends AppCompatActivity {
    public static final String KEY = MuscleGroupsActivity.KEY ;
    private static final String TAG = "Muscule Group";
    public static final String  KEYEXERCISE = "exercise";
    public static final String KEYIMAGE = "image";
    public static final String KEYEXERCISE_TEXT="exercise_text";
    public int musculeGroupN; // int hat say number of muscule
    //List view exercises initialise data
    private ListView lv;
    private String[] exercises=new String[30];
    private int[] imgs=new int[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group_exercises);


        Bundle bundle = getIntent().getExtras();
        musculeGroupN= bundle.getInt(KEY); // Getting number of muscule group from intent
        ImageView image = getImage(musculeGroupN); //Set image to img_main from R.drawable depends on musculeGroupN
        setHeader(musculeGroupN); //Set header from R.string depends on musculeGroupN

        //Test initialise

        setImgs(musculeGroupN);
        setExercises(musculeGroupN);



        //Set data to list view for exercises
        lv= (ListView) findViewById(R.id.list_exercises);
        //Create custom adapter
        ListViewAdapter adapter = new ListViewAdapter(this,exercises,imgs);
        lv.setAdapter(adapter); // set adapter to lv
        //Set on click listener to lv
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),exercises[position],Toast.LENGTH_SHORT).show();
                sendIntent(musculeGroupN,position,imgs[position],exercises[position]);
            }
        });
            String ex1 = getString(R.string.triceps_DymbeliLeg);
            System.out.println(ex1);
        //Toast.makeText(this,"onCreate()",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBackground();
    }

    private void updateBackground(){
        int primaryColor = Integer.parseInt(MainActivity.sharedPreferencesPrimaryColors.getString(MainActivity.colorSPKey,Integer.toString(R.color.colorWhite)));
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.muscule_group_exericses_activity_linear_layout);
        linearLayout.setBackgroundColor(getResources().getColor(primaryColor));
    }

    private void sendIntent(int positionMuscule, int positionExercise, int img, String exercise_text) {
        Intent mIntent = new Intent(this,ExerciseActivity.class);
        mIntent.putExtra(KEY,positionMuscule);
        mIntent.putExtra(KEYEXERCISE,positionExercise);
        mIntent.putExtra(KEYIMAGE,img);
        mIntent.putExtra(KEYEXERCISE_TEXT,exercise_text);
        startActivity(mIntent);
    }

    private void setImgs(int musculeGroupN) {

        switch (musculeGroupN){
            case 0: imgs= new int[]{R.drawable.bedra_katerach,R.drawable.bedra_razgyvane,
                    R.drawable.bedra_sgyvane,R.drawable.bedra_bylgarski_klek,
                    R.drawable.bedra_zatvarqne_abduktor,R.drawable.bedra_klek_shtanga,
                    R.drawable.bedra_leg_presa,R.drawable.bedra_myrtva_tqga,
                    R.drawable.bedra_dumbbell_squat,R.drawable.bedra_otpadi,
                    R.drawable.bedra_jumping_jack,R.drawable.bedra_raztvariane_bedra_aduktor,
                    R.drawable.bedra_skok_klek,R.drawable.bedra_skok_kutia,
                    R.drawable.bedra_shpagat};break;
            case 1: imgs=new int[]{R.drawable.biceps_sgyvane_dolen_skripec,R.drawable.biceps_zotman,
                    R.drawable.biceps_sgyvane_dymbeli,R.drawable.biceps_naklonen_sedej,
                    R.drawable.biceps_shtanga,R.drawable.biceps_sgyvane_dolen_skripec,
                    R.drawable.biceps_koncentrirano,R.drawable.biceps_krystosano_chukovo,
                    R.drawable.biceps_nabirania_neutralen,R.drawable.biceps_nabirania_podhvat,
                    R.drawable.biceps_skotovo,R.drawable.biceps_chukovo};break;
            case 2: imgs=new int[]{R.drawable.gryb_grebane_dymbel_leg,R.drawable.gryb_tshtanga,
                    R.drawable.gryb_gebane_shtanga_nadhvat,R.drawable.gryb_grebane_shtanga_podhvat,
                    R.drawable.gryb_chukcheta,R.drawable.gryb_grebane_dymbel,
                    R.drawable.gryb_ednostrano_pridyrpvane_vertikalen_skripec,R.drawable.gryb_ednostrano_horizontalno_grebane_skripec_sedej,
                    R.drawable.gryb_myrtva_tqga,R.drawable.gryb_nabirania,
                    R.drawable.gryb_goren_skripec_pravi_ryce,R.drawable.gryb_vertikalen_skripec,
                    R.drawable.gryb_goren_skipec_liceto,R.drawable.gryb_dolen_skripec,
                    R.drawable.gryb_pullover,R.drawable.gryb_hiperekstenzia,
                    R.drawable.gryb_raztvarane_peck_deck_mashina}; break;
            case 3: imgs=new int[]{R.drawable.gyrdi_bypari,R.drawable.gyrdi_kofichki,
                    R.drawable.gyrdi_zatvariane_na_ryce_tilen_leg,R.drawable.gyrdi_dymbeli_naklonen_leg,
                    R.drawable.gyrdi_mashina,R.drawable.gyrdi_shtanga_leg_podhvat,
                    R.drawable.gyrdi_krosouvyr_skipec,R.drawable.gyrdi_licevi_opori,
                    R.drawable.gyrdi_peckdeck,R.drawable.gyrdi_dymbeli_leg_obraten,
                    R.drawable.gyrdi_dymbeli_leg_horizontalen,R.drawable.gyrdi_shtanga_obraten,
                    R.drawable.gyrdi_flys,R.drawable.gyrdi_pullover,
                    R.drawable.gyrdi_lejanka,R.drawable.gyrdi_lejanka_poluleg}; break;
            case 4: imgs=new int[]{R.drawable.korem_mahalo,R.drawable.korem_v_up,
                    R.drawable.korem_vertikalno_koremni_tazobedreni_povdigania,R.drawable.korem_grahmar_povdigane,
                    R.drawable.korem_koremni,R.drawable.korem_plank,
                    R.drawable.korem_molitva,R.drawable.korem_rusko_izvivane,
                    R.drawable.korem_spuskane_vdigane_predna_opora,R.drawable.korem_usukvane_pri_povdigane,
                    R.drawable.korem_trenejor}; break;
            case 5: imgs=new int[]{R.drawable.prasec_calf_mashina,R.drawable.prasec_povdigane_na_prasyti_leg_presa,
                    R.drawable.prasec_povdigane_prysti,R.drawable.prasec_jumping_jack,
                    R.drawable.prasec_skok_kutia}; break;
            case 6: imgs=new int[]{R.drawable.predmishnica_bicepsovo_sgyvane_nadhvat,R.drawable.predmishnica_pronirano_razgyvane_kitka,
                    R.drawable.predmishnica_radialna_abdukcia_gira,R.drawable.predmishnica_sgyvane,
                    R.drawable.predmishnica_chukovo}; break;
            case 7: imgs=new int[]{R.drawable.ramo_arnold,R.drawable.ramo_voena_presa,
                    R.drawable.ramo_ryce_vstrani,R.drawable.ramo_ryce_napred,
                    R.drawable.ramo_vertolet,R.drawable.ramo_ruski_swing,
                    R.drawable.ramo_raztvariane_vstrani_naklon_skripec,R.drawable.ramo_raztarane_peck_deck,
                    R.drawable.ramo_presi,R.drawable.ramo_presi_dumbel_sedej}; break;
            case 8: imgs=new int[]{R.drawable.sedalishte_katerach,R.drawable.sedalishte_ekstenzi,
                    R.drawable.sedalishte_klek_shtanga,R.drawable.sedalishte_leg_presa,
                    R.drawable.sedalishte_myrtva_tiaga,R.drawable.sedalishte_napadi,
                    R.drawable.sedalishte_skok_kutia,R.drawable.sedalishte_skok_klek,
                    R.drawable.sedalishte_myrtva_tqga_pravi_kraka}; break;
            case 9: imgs=new int[]{R.drawable.triceps_ednostrano_dumbel_zad_glava,R.drawable.triceps_lejanka,
                    R.drawable.triceps_kik_bek,R.drawable.triceps_kofichki,
                    R.drawable.triceps_polukofichki, R.drawable.triceps_licevi_opori,
                    R.drawable.triceps_triygylnik_licevi, R.drawable.triceps_dymbeli_leg,
                    R.drawable.triceps_razgyvane_goren_skripec, R.drawable.triceps_dymbeli_leg,
                    R.drawable.triceps_frensko}; break;
        }
        //return imgs;
    }

    private void setExercises(int musculeGroupN) {

        switch (musculeGroupN){
            case 0: exercises= new String[]{getString(R.string.bedra_Katerach),getString(R.string.bedra_BedrenoRazgyvane),
                    getString(R.string.bedra_BedrenoSgyvane),getString(R.string.bedra_BylgarskiKlek),
                    getString(R.string.bedra_AduktorMashinaZatvariane),getString(R.string.bedra_KlekShtanga),
                    getString(R.string.bedra_LegPresa),getString(R.string.bedra_MyrtvaTiaga),
                    getString(R.string.bedra_PILE ),getString(R.string.bedra_Otpadi),
                    getString(R.string.bedra_JumpingJack), getString(R.string.bedra_AduktorMashinaRaztvariane),
                    getString(R.string.bedra_SkokKlek), getString(R.string.bedra_SkokKutia),
                    getString(R.string.bedra_Shpagat)}; break;
            case 1: exercises=new String[]{getString(R.string.biceps_dolenSkripec),getString(R.string.biceps_Zotman),
                    getString(R.string.biceps_SgyvaneDymbeli),getString(R.string.biceps_DymbeliNakolnenSedej),
                    getString(R.string.biceps_shtanga),getString(R.string.biceps_PortalenSkripec),
                    getString(R.string.biceps_KoncentriranoSgyvane),getString(R.string.biceps_ChukovoKrystosano),
                    getString(R.string.biceps_NabiraneNeutralen),getString(R.string.biceps_NabiranePOdhvat),
                    getString(R.string.biceps_SkotovoSgyvane),getString(R.string.biceps_Chukovo)}; break;
            case 2: exercises=new String[]{getString(R.string.gryb_GrebaneDymbeliLeg),getString(R.string.gryb_TShtanga),
                    getString(R.string.gryb_GrebaneShtangaNapredNadhvat),getString(R.string.gryb_GrebaneShtangaNapredPodhvat),
                    getString(R.string.gryb_GrebaneShtanga),getString(R.string.gryb_GrebaneDymbel),
                    getString(R.string.gryb_EdnostranoPridyrpvaneVertikalenSkripec),getString(R.string.gryb_EdnostranoHorizontalnoGrebaneSkripecSedej),
                    getString(R.string.gryb_MyrtvaTiaga),getString(R.string.gryb_Nabirania),
                    getString(R.string.gryb_GorenSkripec),getString(R.string.gryb_VertikalenSkripecShirokHvat),
                    getString(R.string.gryb_SkripecKymLiceto),getString(R.string.gryb_DolenSkripec),
                    getString(R.string.gryb_Pullover),getString(R.string.gryb_Hyperextension),
                    getString(R.string.gryb_PekDekRaztvariane)}; break;
            case 3: exercises=new String[]{getString(R.string.gyrdi_bypari),getString(R.string.gyrdi_Kofichki),
                    getString(R.string.gyrdi_ZatvarianeNaRyceTilenLEg),getString(R.string.gyrdi_DymbeliNakolnenLeg),
                    getString(R.string.gyrdi_MashinaGrydniMuskuli),getString(R.string.gyrdi_ShtangaLegPodhvat),
                    getString(R.string.gyrdi_KrosouvyrGyrdi),getString(R.string.gyrdi_LiceviOpori),
                    getString(R.string.gyrdi_PeckDeck),getString(R.string.gyrdi_DymbeliObratenNakoln),
                    getString(R.string.gyrdi_DymbeliHorizontalen),getString(R.string.gyrdi_ShtangaObratenNaklon),
                    getString(R.string.gyrdi_Flais),getString(R.string.gyrdi_Pulovar),
                    getString(R.string.gyrdi_lejanka),getString(R.string.gyrdi_ShtangaPoluLeg)}; break;
            case 4: exercises=new String[]{getString(R.string.korem_Mahalo),getString(R.string.korem_VPresa),
                    getString(R.string.korem_VertikalniPovdiganiq),getString(R.string.korem_GarhmyrPovigane),
                    getString(R.string.korem_Presi),getString(R.string.korem_Pank),
                    getString(R.string.korem_Molitva),getString(R.string.korem_RuskoIzvivane),
                    getString(R.string.korem_Roler),getString(R.string.korem_UsukvanePriVdigane),
                    getString(R.string.korem_Trenejor)}; break;
            case 5: exercises=new String[]{getString(R.string.prasec_KalfMashina),getString(R.string.prasec_PrystiLegPresa),
                    getString(R.string.prasec_PovdiganeNaPrysti),getString(R.string.prasec_JumpingJack),
                    getString(R.string.prasec_SkokVyrhuKutiq)}; break;
            case 6: exercises=new String[]{getString(R.string.predmishnici_BicepsovoSgyvaneNadhvat),getString(R.string.predmishnici_ProniranoRazgyvane),
                    getString(R.string.predmishnici_RadialnaAbdukcia),getString(R.string.predmishnici_SgyvanePredmishnici),
                    getString(R.string.predmishnici_Chukovo)}; break;
            case 7: exercises=new String[]{getString(R.string.ramo_Arnold),getString(R.string.ramo_VoenaPresa),
                    getString(R.string.ramo_RyceStrani),getString(R.string.ramo_RyceNapred),
                    getString(R.string.ramo_Vertolet),getString(R.string.ramo_RuskiSwing),
                    getString(R.string.ramo_RaztvarianeVstraniNaklon),getString(R.string.ramo_RaztvarianePeckDeck),
                    getString(R.string.ramo_Presi),getString(R.string.ramo_PresiDymbel)}; break;
            case 8: exercises=new String[]{getString(R.string.sedalishte_PlaninskiKaterach),getString(R.string.sedalishte_Ekstenziq),
                    getString(R.string.sedalishte_Klek),getString(R.string.sedalishte_LegPresa),
                    getString(R.string.sedalishte_MyrtvaTqga),getString(R.string.sedalishte_Napadi),
                    getString(R.string.sedalishte_BoxJump),getString(R.string.sedalishte_SkokKlek),
                    getString(R.string.sedalishte_TqgaPraviKraka)}; break;
            case 9: exercises=new String[]{getString(R.string.triceps_RazgyvaneZadGlava),getString(R.string.triceps_Lejanka),
                    getString(R.string.triceps_KickBack),getString(R.string.triceps_Kofichi),
                    getString(R.string.triceps_KofichkiPeika),
                    getString(R.string.triceps_LiceviOpori),getString(R.string.triceps_LiceviTriygylnik),
                    getString(R.string.triceps_DymbeliLeg),getString(R.string.triceps_GorenSkripec),
                    getString(R.string.triceps_RazgyvaneDymbeliLeg),getString(R.string.triceps_Frensko)}; break;
        }

    }

    private ImageView getImage(int musculeGroupN) { //Set image depends musculeGroupNimage
        ImageView image = (ImageView) findViewById(R.id.img_main);
        switch (musculeGroupN){
            case 0: image.setImageResource(R.drawable.bedra); break;
            case 1: image.setImageResource(R.drawable.biceps); break;
            case 2: image.setImageResource(R.drawable.gryb); break;
            case 3: image.setImageResource(R.drawable.gyrdi); break;
            case 4: image.setImageResource(R.drawable.korem); break;
            case 5: image.setImageResource(R.drawable.prasec); break;
            case 6: image.setImageResource(R.drawable.predmishnica); break;
            case 7: image.setImageResource(R.drawable.ramo); break;
            case 8: image.setImageResource(R.drawable.sedalishte); break;
            case 9: image.setImageResource(R.drawable.triceps); break;
        }

        return image;
    }

    private void setHeader(int musculeGroupN){ //Set text to TextView Header and set layout header

        switch (musculeGroupN){
            case 0:  setTitle(R.string.Bedra); break;
            case 1:  setTitle(R.string.Biceps); break;
            case 2:  setTitle(R.string.Gryb); break;
            case 3:  setTitle(R.string.Gyrdi); break;
            case 4:  setTitle(R.string.Korem); break;
            case 5:  setTitle(R.string.Prasec); break;
            case 6:  setTitle(R.string.Predmishnici); break;
            case 7:  setTitle(R.string.Ramo); break;
            case 8:  setTitle(R.string.Sedalishte); break;
            case 9:  setTitle(R.string.Triceps); break;
        }

    }






}


