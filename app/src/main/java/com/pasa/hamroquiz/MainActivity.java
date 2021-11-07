package com.pasa.hamroquiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel, questionLabel;
    private Button answerBtn1, answerBtn2, answerBtn3, answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 13;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
            // {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
            {"कूल दाईका अनुसार याबिनको नाम के हो?", "ठग", "शरारती", "बदमाश", "गुंडा"},
            {"ह्रिकेश दाईका अनुसार अभाशको नाम के हो?", "पाशा", "माक", "सैंजु", "मखुला"},
            {"याबीन, गणेश दाई र सरकार मध्ये कसले पहिले इजाजतपत्र निकालेका थिए?", "याबिन", "सरकार", "गणेश दाई", "कसैले निकालेन"},
            {"याबिनको जीवन समर्थनलाई कुन नामले चिनिन्छ?", "Maruko", "Nyaan", "Sunflowr", "0_0"},
            {"चन्दन दाईका अनुसार कुन गीत सरकारका लागि परिमार्जन गरिएको थियो?", "नीरा जहिले रिसाउने", "असारै महिनामा", "कसरी भनु तिमीलाइ", "हवाइजहाज"},
            {"याविनले मरुकोकाे जन्मदिनमा कुन गीत गााएकाे थियाे?", "चिठी भित्र", "मायामा", "मुस्कान", "हाम्रो नेपालमा"},
            {"सबैभन्दा राम्री दीदिक भनेर कसलाइ चिनिन्छ?", "युमी दी", "निधि दीदि", "रजनी दी", "रिम्शा दीदि"},
            {"d4 को दिदीहरूमा PUBGको अधिक रुचि काे हुनुहुन्छ?", "रजनी दी", "युमी दी", "नाबिना दीदि", "कोही पनि हैन"},
            {"दिपिन दाई को अनूसर, ________ लाईफ भान्डा खासी कइले ठूलाे हुदैन", "CIG", "THUG", "STUDENT", "MOBILE"},
            {"निधि दिदी को अनुसार, सरकार को नाम के हो?", "गधेन्द्र", "पाठक", "PHOTOGRAPHER", "DSLR"},
            {"'HIGH PROFILES MEDIOCRE MISTAKEN FOR A GENIUS!!' यो status को संग सम्बन्धित छ?", "निश्चल दाई", "याबिन", "कुल दाई", "सन्जन दाई"},
            {"'C R E P U S C U L O' यो के संग सम्बन्धित छ?", "मेरो तस्विर", "याबिन्को मनपर्ने MOVIE", "युमी दीकाे ART WORK", "सरकारकाे PHOTOGRAPHY HASHTAG"},
            {"सरकारकाे अनुसार युमी दीदीको नाम के हो?", "युमु", "युमेन्द्र", "YUMMY", "यमुनेंद्र"},
            {"याबिनको दोस्रो झ्याप अनुभव कहिले थियो?", "11/15/2019", "10/15/2019", "09/15/2019", "08/15/2019"},
            {"दिदीहरू माध्य विदेशी भाषामा कासले माइनर गार्दै हनुहुन्छ?", "नाबिना दीदि", "सुकृति दीदि", "एलिना दीदि", "युमी दी"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        // Receive quizCategory from StartActivity.
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY", quizCategory + "");


        // Create quizArray from quizData.
        for (int i = 0; i < quizData.length; i++) {

            // Prepare array.
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); // Country
            tmpArray.add(quizData[i][1]); // Right Answer
            tmpArray.add(quizData[i][2]); // Choice1
            tmpArray.add(quizData[i][3]); // Choice2
            tmpArray.add(quizData[i][4]); // Choice3

            // Add tmpArray to quizArray.
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz() {

        // Update quizCountLabel.
        countLabel.setText("प्रश्न संख्या " + quizCount);

        // Generate random number between 0 and 14 (quizArray's size - 1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        // Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        // Set question and right answer.
        // Array format: {"Country", "Right Answer", "Choice1", "Choice2", "Choice3"}
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        // Remove "Country" from quiz and Shuffle choices.
        quiz.remove(0);
        Collections.shuffle(quiz);

        // Set choices.
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        // Remove this quiz from quizArray.
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {

        // Get pushed button.
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if (btnText.equals(rightAnswer)) {
            // Correct हजारमा कुरा मिल्यो
            alertTitle = "ठ्याक्कै मिल्यो!";
            rightAnswerCount++;

        } else {
            alertTitle = "मिलेन!";
        }

        // Create AlertDialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("सहि उत्तर : " + rightAnswer);
        builder.setPositiveButton("ठिक छ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT) {
                    // Show Result.
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

}