package com.toeic;

import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXToggleButton;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.*;

public class Part6_7Form extends PartForm{
    public static final String MENU_HEADER = "TOEIC PART7";

    // 10 minutes for 16 questions -> approx 37.5 seconds per question
    private static final double PART6_TIME = 37500.0;

    private static final double PART6_PER_TIME = 60000.0;
    // 55 minutes for 54 questions -> approx 1 min per question (60 * 1000)

    private static final double PART7_TIME = 60000.0;

    private static final int PART7_HEIGHT = 780;
    private static final int PART7_WIDTH = 1360;

    private static final String TAB_0 = "Single";
    private static final String TAB_01 = "Double";
    private static final String TAB_02 = "Triple";
    public static final String ARE_YOU_SURE_TO_CHANGE_THE_PAGE = "Are you sure to change the page?";
    public static final String QUESTION_1 = "question1";
    public static final String QUESTION_2 = "question2";
    public static final String QUESTION_3 = "question3";
    public static final String QUESTION_4 = "question4";
    public static final String QUESTION_5 = "question5";
    public static final String QUESTION_6 = "question6";
    public static final String QUESTION_7 = "question7";
    public static final String QUESTION_8 = "question8";
    public static final String QUESTION_9 = "question9";
    public static final String QUESTION_10 = "question10";

    public static final String QUESTION_IMG_VIEW_1 = "questionImgView1";
    public static final String QUESTION_IMG_VIEW_2 = "questionImgView2";
    public static final String QUESTION_IMG_VIEW_3 = "questionImgView3";
    public static final String QUESTION_IMG_VIEW_4 = "questionImgView4";
    public static final String QUESTION_IMG_VIEW_5 = "questionImgView5";
    public static final String QUESTION_IMG_VIEW_6 = "questionImgView6";
    public static final String QUESTION_IMG_VIEW_7 = "questionImgView7";
    public static final String QUESTION_IMG_VIEW_8 = "questionImgView8";
    public static final String QUESTION_IMG_VIEW_9 = "questionImgView9";
    public static final String QUESTION_IMG_VIEW_10 = "questionImgView10";

    private Background bg;

    private boolean m_stageShowing = false;

    private boolean isExpanded = false;
    private boolean isNoWarningDialog = true;

    private boolean isSliderHidden = true;
    private boolean isTail = false;

    private boolean isPart7showAnswer = false;
    private boolean isInitialPrevSourceId = true;
    private boolean skipRenewQuestions = false;

    private BorderPane border;
    private BorderPane rightLayout;

    private ComboBox sourceComboBox;

    private ContextMenu popupMenu =null;

    private CustomDialog warningDialog;

    private double sliderWidth = 550.0; // Initial width of the sidebar

    private String previousSourceId = "";

    private Properties property = null;

    private ImageView expand;
    private ImageView shrink;
    private ImageView bookMark;
    private ImageView review1;
    private ImageView review2;
    private ImageView review3;
    private ImageView review4;
    private ImageView review5;
    private ImageView review6;
    private ImageView review7;
    private ImageView review8;
    private ImageView review9;
    private ImageView review10;
    private ImageView result1;
    private ImageView result2;
    private ImageView result3;
    private ImageView result4;
    private ImageView result5;
    private ImageView result6;
    private ImageView result7;
    private ImageView result8;
    private ImageView result9;
    private ImageView result10;

    private ImageView singleTabImage;

    private Label resultDate1;
    private Label resultDate2;
    private Label resultDate3;
    private Label resultDate4;
    private Label resultDate5;
    private Label resultDate6;
    private Label resultDate7;
    private Label resultDate8;
    private Label resultDate9;
    private Label resultDate10;

    private Label examinationType;

    private SortedMap<String, Integer> sourceHeadMap;
    private SortedMap<String, Integer> sourceTailMap;

    private SortedMap<String, HistoryRecord> historyRecordSortedMap;

    private SplitPane splitPane;

    private JFXTabPane tabPaneLayout;

    private Media media;
    private MediaPlayer mediaPlayer;

    private MenuButton menuButton;

    private ObservableList<String> sourceList = FXCollections.observableArrayList();

    private RadioButton radioButton1A;
    private RadioButton radioButton1B;
    private RadioButton radioButton1C;
    private RadioButton radioButton1D;

    private RadioButton radioButton2A;
    private RadioButton radioButton2B;
    private RadioButton radioButton2C;
    private RadioButton radioButton2D;

    private RadioButton radioButton3A;
    private RadioButton radioButton3B;
    private RadioButton radioButton3C;
    private RadioButton radioButton3D;

    private RadioButton radioButton4A;
    private RadioButton radioButton4B;
    private RadioButton radioButton4C;
    private RadioButton radioButton4D;

    private RadioButton radioButton5A;
    private RadioButton radioButton5B;
    private RadioButton radioButton5C;
    private RadioButton radioButton5D;

    private RadioButton radioButton6A;
    private RadioButton radioButton6B;
    private RadioButton radioButton6C;
    private RadioButton radioButton6D;

    private RadioButton radioButton7A;
    private RadioButton radioButton7B;
    private RadioButton radioButton7C;
    private RadioButton radioButton7D;

    private RadioButton radioButton8A;
    private RadioButton radioButton8B;
    private RadioButton radioButton8C;
    private RadioButton radioButton8D;

    private RadioButton radioButton9A;
    private RadioButton radioButton9B;
    private RadioButton radioButton9C;
    private RadioButton radioButton9D;

    private RadioButton radioButton10A;
    private RadioButton radioButton10B;
    private RadioButton radioButton10C;
    private RadioButton radioButton10D;

    private SplitMenuButton splitMenuButton;

    private String part;

    private Tab singleTab;
    private Tab doubleTab;
    private Tab tripleTab;

    private TimerLayout timerLayout;
    private VBox topLeftLayout;
    private VBox topRightLayout;

    private VBox buttonAttached;

    Scene scene;

    /**
     * Constructor
     * @param stage
     * @param root
     * @param numberOfRow
     * @param parts
     * @param lastRowNum
     */
    public Part6_7Form(Stage stage, Pane root, int numberOfRow, List<Part> parts, int lastRowNum,
                       HistoryRecordFile historyRecordFile, ResultManagement resultManagement,
                       ReviewManagement reviewManagement) {
        super(numberOfRow, parts, lastRowNum, stage, root, historyRecordFile, resultManagement, reviewManagement);

        sourceHeadMap = new TreeMap<>();
        sourceTailMap = new TreeMap<>();
        for(Part part : parts)
        {
            sourceHeadMap.putIfAbsent(part.getSource(), Integer.valueOf(part.getPartListNumber()));
            sourceTailMap.put(part.getSource(), Integer.valueOf(part.getPartListNumber()));

            reviewManagement.updateResultRecord(part.getItemNum(), part.getItemNum(), part.getPart(), part.getReview(),true);
        }

        popupMenu = new ContextMenu();
        part = parts.get(0).getPart();

        questionImgView1 = new ImageView();
        questionImgView1.setPreserveRatio(true);
        questionImgView1.setFitHeight(180);
        questionImgView1.setFitWidth(400);
        questionImgView2 = new ImageView();
        questionImgView2.setPreserveRatio(true);
        questionImgView2.setFitHeight(180);
        questionImgView2.setFitWidth(400);
        questionImgView3 = new ImageView();
        questionImgView3.setPreserveRatio(true);
        questionImgView3.setFitHeight(180);
        questionImgView3.setFitWidth(400);
        questionImgView4 = new ImageView();
        questionImgView4.setPreserveRatio(true);
        questionImgView4.setFitHeight(180);
        questionImgView4.setFitWidth(400);
        questionImgView5 = new ImageView();
        questionImgView5.setPreserveRatio(true);
        questionImgView5.setFitHeight(180);
        questionImgView5.setFitWidth(400);
        questionImgView6 = new ImageView();
        questionImgView6.setPreserveRatio(true);
        questionImgView6.setFitHeight(180);
        questionImgView6.setFitWidth(400);
        questionImgView7 = new ImageView();
        questionImgView7.setPreserveRatio(true);
        questionImgView7.setFitHeight(180);
        questionImgView7.setFitWidth(400);
        questionImgView8 = new ImageView();
        questionImgView8.setPreserveRatio(true);
        questionImgView8.setFitHeight(180);
        questionImgView8.setFitWidth(400);
        questionImgView9 = new ImageView();
        questionImgView9.setPreserveRatio(true);
        questionImgView9.setFitHeight(180);
        questionImgView9.setFitWidth(400);
        questionImgView10 = new ImageView();
        questionImgView10.setPreserveRatio(true);
        questionImgView10.setFitHeight(180);
        questionImgView10.setFitWidth(400);

        question1 = new Label();
        question2 = new Label();
        question3 = new Label();
        question4 = new Label();
        question5 = new Label();
        question6 = new Label();
        question7 = new Label();
        question8 = new Label();
        question9 = new Label();
        question10 = new Label();
    }

    /**
     * Getters and Setters
     * @return
     */
    public BorderPane getLayout() {
        return border;
    }

    public TimerLayout getTimerLayout() { return timerLayout; }

    public boolean isSkipRenewQuestions() {
        return skipRenewQuestions;
    }

    public void setSkipRenewQuestions(boolean skipRenewQuestions) {
        this.skipRenewQuestions = skipRenewQuestions;
    }


    public boolean isTail() { return isTail; }

    public void setTail(boolean tail) { isTail = tail; }

    public boolean isPart7showAnswer() { return isPart7showAnswer; }

    public void setPart7showAnswer(boolean part7showAnswer) { isPart7showAnswer = part7showAnswer; }

    public void setProperty(Properties property) { this.property = property; }

    @Override
    protected void assignActionEvent() {
        startButton.setOnAction(e -> {
            mediaPlayer = new MediaPlayer(this.getMedia());
            mediaPlayer.play();
            startButton.disableProperty().set(true);

            // Once the player is finished
            mediaPlayer.setOnEndOfMedia( () -> {
                // Enable the startButton for the media-play
                if (startButton.disableProperty().get()) {
                    startButton.disableProperty().set(false);
                }
            });
        });

        backButton.setOnAction(e -> {
            ProcessToGoBackToPrevious();
        });

        bookMark.setOnMousePressed(e -> {
            historyRecordFile.updateHistoryRecord(parts.get(numberOfRow).getItemNum(),
                    numberOfRow, parts.get(numberOfRow).getPart());

            if (checkBookMark(numberOfRow))
                startSound("resources/Click.mp3");
        });

        nextButton.setOnAction(e -> {
            ProcessToGoToNextPage();
        });

        homeButton.setOnAction(e -> {
            makeFadeOut();
        });

        answer1Button.setOnAction(e -> {
            showExplanation(numberOfRow);
        });

        answer2Button.setOnAction( e-> {
            showExplanation(numberOfRow +1);
        });

        answer3Button.setOnAction( e-> {
            showExplanation(numberOfRow +2);
        });

        answer4Button.setOnAction( e-> {
            showExplanation(numberOfRow +3);
        });

        answer5Button.setOnAction( e-> {
            showExplanation(numberOfRow +4);
        });

        answer6Button.setOnAction( e-> {
            showExplanation(numberOfRow +5);
        });

        answer7Button.setOnAction( e-> {
            showExplanation(numberOfRow +6);
        });

        answer8Button.setOnAction( e-> {
            showExplanation(numberOfRow +7);
        });

        answer9Button.setOnAction( e-> {
            showExplanation(numberOfRow +8);
        });

        answer10Button.setOnAction( e-> {
            showExplanation(numberOfRow +9);
        });


       sourceComboBox.setOnAction(e -> {
           if (!skipRenewQuestions)
               renewQuestionsMarkedRecord();
        });

        listening1Button.setOnAction(e -> {
            mediaPlayer = new MediaPlayer(this.getMedia());
            mediaPlayer.play();
            listening1Button.disableProperty().set(true);

            // Once the player is finished
            mediaPlayer.setOnEndOfMedia( () -> {
                // Enable the startButton for the media-play
                if (listening1Button.disableProperty().get()) {
                    listening1Button.disableProperty().set(false);
                }
            });
        });

        resizeButton.setOnAction(e -> {
            if(isExpanded)
                  resizeButton.setGraphic(expand);
            else resizeButton.setGraphic(shrink);

            toggleSidebar();
        });

        textbookButton.setOnAction(e-> {
            this.showFolderContentsDialog(numberOfRow);
        });

        //ポップアップメニューがリクエストされた場合の処理
        question1.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_1);
            popupMenu.show(question1, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question1.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question2.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_2);
            popupMenu.show(question2, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question2.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question3.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_3);
            popupMenu.show(question3, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question3.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question4.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_4);
            popupMenu.show(question4, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question4.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question5.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_5);
            popupMenu.show(question5, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question5.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question6.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_6);
            popupMenu.show(question6, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question6.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question7.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_7);
            popupMenu.show(question7, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question7.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question8.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_8);
            popupMenu.show(question8, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question8.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question9.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_9);
            popupMenu.show(question9, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question9.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        question10.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_10);
            popupMenu.show(question10, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        question10.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView1.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_1);
            popupMenu.show(questionImgView1, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView1.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView2.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_2);
            popupMenu.show(questionImgView2, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView2.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView3.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_3);
            popupMenu.show(questionImgView3, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView3.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView4.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_4);
            popupMenu.show(questionImgView4, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView4.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView5.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_5);
            popupMenu.show(questionImgView5, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView5.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView6.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_6);
            popupMenu.show(questionImgView6, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView6.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView7.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_7);
            popupMenu.show(questionImgView7, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView7.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView8.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_8);
            popupMenu.show(questionImgView8, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView8.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView9.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_9);
            popupMenu.show(questionImgView9, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView9.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        //ポップアップメニューがリクエストされた場合の処理
        questionImgView10.setOnContextMenuRequested((ContextMenuEvent event) -> {
            popupMenu.hide();
            popupMenu = createPopupMenu(QUESTION_IMG_VIEW_10);
            popupMenu.show(questionImgView10, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        //マウスクリックの場合、ポップアップメニューを不可視にする
        questionImgView10.setOnMouseClicked((MouseEvent event) -> { popupMenu.hide(); });

        toggleShowResult.setOnAction(e -> {
            if(toggleShowResult.isSelected())
                this.setPart7showAnswer(true);
            else
                this.setPart7showAnswer(false);

            restoreRadioButtons(numberOfRow, numberOfRow+1, numberOfRow+2,
                    numberOfRow+3, numberOfRow+4, numberOfRow+5,
                    numberOfRow+6, numberOfRow+7, numberOfRow+8,
                    numberOfRow+9);
        });
    }

    private void assignTimeLimit(int row, String timeDefined) {
            double timeLimit = (double) Integer.parseInt(property.getProperty(timeDefined));
            if (!parts.get(row).getSource().matches(sourceComboBox.getValue().toString())) {
            if (part.matches("6"))
                timerLayout.setTimelimit(PART6_PER_TIME * timeLimit);
            else
                timerLayout.setTimelimit(PART7_TIME * timeLimit);
            } else {
            if (timerLayout.getTimelimit() != timeLimit) {
                if (part.matches("6"))
                    timerLayout.setTimelimit(PART6_PER_TIME * timeLimit);
                else
                    timerLayout.setTimelimit(PART7_TIME * timeLimit);
            }
        }
    }
    private void buildUpPartListView() {
        for(Map.Entry mapEntry : sourceHeadMap.entrySet()) {
            sourceList.add(mapEntry.getKey().toString());
        }
    }

    @Override
    protected void changeImageOrTextField(int row) {
        String questionNum = parts.get(row).getQuestionNum();

        // single question
        if(parts.get(row).getQuestAttrib().matches("F")) {
            questionImgView1.setImage(createImage(parts.get(row).getFilePath()+
                    parts.get(row).getQuestion()));
            changeQestionOpacity(0.0, 1.0, question1, questionImgView1);
        } else {
            question1.setText(this.questionText1);
            changeQestionOpacity(1.0, 0.0, question1, questionImgView1);
        }

        // 2 - 5 questions
        int row2 = row + 1;
        if(Integer.valueOf(questionNum) > 1) {
            if (parts.get(row2).getQuestAttrib().matches("F")) {
                questionImgView2.setImage(createImage(parts.get(row2).getFilePath() +
                        parts.get(row2).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question2, questionImgView2);
            } else {
                question2.setText(this.questionText2);
                changeQestionOpacity(1.0, 0.0, question2, questionImgView2);
            }
        }

        // 3 - 5 questions
        int row3 = row + 2;
        if(Integer.valueOf(questionNum) > 2) {
            if (parts.get(row3).getQuestAttrib().matches("F")) {
                questionImgView3.setImage(createImage(parts.get(row3).getFilePath() +
                        parts.get(row3).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question3, questionImgView3);
            } else {
                question3.setText(this.questionText3);
                changeQestionOpacity(1.0, 0.0, question3, questionImgView3);
            }
        }

        // 4 - 5 questions
        int row4 = row + 3;
        if(Integer.valueOf(questionNum) > 3) {
            if (parts.get(row4).getQuestAttrib().matches("F")) {
                questionImgView4.setImage(createImage(parts.get(row4).getFilePath() +
                        parts.get(row4).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question4, questionImgView4);
            } else {
                question4.setText(this.questionText4);
                changeQestionOpacity(1.0, 0.0, question4, questionImgView4);
            }
        }

        // 5 questions
        int row5 = row + 4;
        if(Integer.valueOf(questionNum) > 4) {
            if (parts.get(row5).getQuestAttrib().matches("F")) {
                questionImgView5.setImage(createImage(parts.get(row5).getFilePath() +
                        parts.get(row5).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question5, questionImgView5);
            } else {
                question5.setText(this.questionText5);
                changeQestionOpacity(1.0, 0.0, question5, questionImgView5);
            }
        }

        // 6 questions
        int row6 = row + 5;
        if(Integer.valueOf(questionNum) > 5) {
            if (parts.get(row6).getQuestAttrib().matches("F")) {
                questionImgView6.setImage(createImage(parts.get(row6).getFilePath() +
                        parts.get(row6).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question6, questionImgView6);
            } else {
                question6.setText(this.questionText6);
                changeQestionOpacity(1.0, 0.0, question6, questionImgView6);
            }
        }

        // 7 questions
        int row7 = row + 6;
        if(Integer.valueOf(questionNum) > 6) {
            if (parts.get(row7).getQuestAttrib().matches("F")) {
                questionImgView7.setImage(createImage(parts.get(row7).getFilePath() +
                        parts.get(row7).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question7, questionImgView7);
            } else {
                question7.setText(this.questionText7);
                changeQestionOpacity(1.0, 0.0, question7, questionImgView7);
            }
        }

        // 8 questions
        int row8 = row + 7;
        if(Integer.valueOf(questionNum) > 7) {
            if (parts.get(row8).getQuestAttrib().matches("F")) {
                questionImgView8.setImage(createImage(parts.get(row8).getFilePath() +
                        parts.get(row8).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question8, questionImgView8);
            } else {
                question8.setText(this.questionText8);
                changeQestionOpacity(1.0, 0.0, question8, questionImgView8);
            }
        }

        // 9 questions
        int row9 = row +8;
        if(Integer.valueOf(questionNum) > 8) {
            if (parts.get(row9).getQuestAttrib().matches("F")) {
                questionImgView9.setImage(createImage(parts.get(row9).getFilePath() +
                        parts.get(row9).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question9, questionImgView9);
            } else {
                question9.setText(this.questionText9);
                changeQestionOpacity(1.0, 0.0, question9, questionImgView9);
            }
        }

        // 10 questions
        int row10 = row +9;
        if(Integer.valueOf(questionNum) > 9) {
            if (parts.get(row10).getQuestAttrib().matches("F")) {
                questionImgView10.setImage(createImage(parts.get(row10).getFilePath() +
                        parts.get(row10).getQuestion()));
                changeQestionOpacity(0.0, 1.0, question10, questionImgView10);
            } else {
                question10.setText(this.questionText10);
                changeQestionOpacity(1.0, 0.0, question10, questionImgView10);
            }
        }
    }

    private void changeRadioButtonProperties(Toggle oldVal, Toggle newVal, String answer, int numberOfRow) {
        String result;
        if(newVal != null) {
            // Cast object to radio button
            RadioButton chk = (RadioButton) newVal.getToggleGroup().getSelectedToggle();

            if (chk.getText().matches(answer)) {
                errataPieChart.setRecord(parts.get(numberOfRow).getItemNum(), true, chk.getText(),
                        parts.get(numberOfRow).getPart(), parts.get(numberOfRow).getSource());
                if(this.isPart7showAnswer)
                    chk.setStyle("-fx-background-color:GREEN; -fx-background-radius: 15px; -fx-background-width: 2px;");
                result = PASS;
            } else {
                errataPieChart.setRecord(parts.get(numberOfRow).getItemNum(), false, chk.getText(),
                        parts.get(numberOfRow).getPart(), parts.get(numberOfRow).getSource());
                if(this.isPart7showAnswer)
                    chk.setStyle("-fx-background-color:RED; -fx-background-radius: 15px; -fx-background-width: 2px;");
                result = FAIL;
            }
            resultManagement.updateResultRecord(parts.get(numberOfRow).getItemNum(),
                    parts.get(numberOfRow).getPart(), result, chk.getText());
        }

        // Reset the background to transparent
        if( oldVal != null && oldVal instanceof RadioButton) { // Avoid to access to the null object
            // Cast object to radio button
            RadioButton oldChk = (RadioButton) oldVal;
            if(this.isPart7showAnswer)
                oldChk.setStyle("-fx-background-color:null; -fx-background-radius: 15px; -fx-background-width: 2px;");
        }
    }

    private boolean checkBookMark(int numberOfRow) {
        boolean marked = false;
        if (historyRecordFile.isBookMarkRecord(numberOfRow, parts.get(numberOfRow).getPart())) {
            bookMark.setOpacity(1.0);
            marked = true;
        } else {  // if not BookMarkedRecord
            bookMark.setOpacity(0.0);
        }
        return marked;
    }

    protected VBox createDocumentVBox(int row, String attrib, String fileName, String path, double screenHeight,
                                      double screenWidth) {
        singleTabImage = null;
        alertBox.setWindowHeight(820);
        alertBox.setWindowWidth(830);
        alertBox.setScreenHeight(screenHeight);
        alertBox.setScreenWidth(screenWidth);
        return alertBox.getImageTextContents(attrib, fileName, path, true, singleTabImage, tabPaneLayout);
    }
    @Override
    protected void createGroupControls() {
        changeImageOrTextField(this.numberOfRow);

        radioButton1A.setToggleGroup(group1);
        radioButton1B.setToggleGroup(group1);
        radioButton1C.setToggleGroup(group1);
        radioButton1D.setToggleGroup(group1);

        group1.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer1, numberOfRow);
        });

        radioButton2A.setToggleGroup(group2);
        radioButton2B.setToggleGroup(group2);
        radioButton2C.setToggleGroup(group2);
        radioButton2D.setToggleGroup(group2);

        group2.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer2, numberOfRow+1);
        });

        radioButton3A.setToggleGroup(group3);
        radioButton3B.setToggleGroup(group3);
        radioButton3C.setToggleGroup(group3);
        radioButton3D.setToggleGroup(group3);

        group3.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer3, numberOfRow+2);
        });

        radioButton4A.setToggleGroup(group4);
        radioButton4B.setToggleGroup(group4);
        radioButton4C.setToggleGroup(group4);
        radioButton4D.setToggleGroup(group4);

        group4.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer4, numberOfRow+3);
        });

        radioButton5A.setToggleGroup(group5);
        radioButton5B.setToggleGroup(group5);
        radioButton5C.setToggleGroup(group5);
        radioButton5D.setToggleGroup(group5);

        group5.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer5, numberOfRow+4);
        });

        radioButton6A.setToggleGroup(group6);
        radioButton6B.setToggleGroup(group6);
        radioButton6C.setToggleGroup(group6);
        radioButton6D.setToggleGroup(group6);

        group6.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer6, numberOfRow+5);
        });

        radioButton7A.setToggleGroup(group7);
        radioButton7B.setToggleGroup(group7);
        radioButton7C.setToggleGroup(group7);
        radioButton7D.setToggleGroup(group7);

        group7.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer7, numberOfRow+6);
        });

        radioButton8A.setToggleGroup(group8);
        radioButton8B.setToggleGroup(group8);
        radioButton8C.setToggleGroup(group8);
        radioButton8D.setToggleGroup(group8);

        group8.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer8, numberOfRow+7);
        });

        radioButton9A.setToggleGroup(group9);
        radioButton9B.setToggleGroup(group9);
        radioButton9C.setToggleGroup(group9);
        radioButton9D.setToggleGroup(group9);

        group9.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer9, numberOfRow+8);
        });

        radioButton10A.setToggleGroup(group10);
        radioButton10B.setToggleGroup(group10);
        radioButton10C.setToggleGroup(group10);
        radioButton10D.setToggleGroup(group10);

        group10.selectedToggleProperty().addListener((ObservableValue<? extends Toggle>
                                                             observ, Toggle oldVal, Toggle newVal)->{
            changeRadioButtonProperties(oldVal, newVal, this.answer10, numberOfRow+9);
        });
    }

    ContextMenu createPopupMenu(String name) {
        ContextMenu menu = new  ContextMenu();
        MenuItem[] menui =null;

        menui = new MenuItem[2];
        menui[0] = new MenuItem("Mark");
        menui[1] = new MenuItem("Unmark");
        menu.getItems().addAll(menui);

        for(int i=0;i<menui.length;i++){
            menui[i].addEventHandler( ActionEvent.ACTION ,
                    e -> {
                        String selection =((MenuItem) e.getSource()).getText();
                        updateViewImages(name, selection);
                    });
        }
        return menu;
    }

    @Override
    protected void createRadioButtons() {
        group1 = new ToggleGroup();
        radioButton1A = new RadioButton("A");
        radioButton1B = new RadioButton("B");
        radioButton1C = new RadioButton("C");
        radioButton1D = new RadioButton("D");

        group2 = new ToggleGroup();
        radioButton2A = new RadioButton("A");
        radioButton2B = new RadioButton("B");
        radioButton2C = new RadioButton("C");
        radioButton2D = new RadioButton("D");

        group3 = new ToggleGroup();
        radioButton3A = new RadioButton("A");
        radioButton3B = new RadioButton("B");
        radioButton3C = new RadioButton("C");
        radioButton3D = new RadioButton("D");

        group4 = new ToggleGroup();
        radioButton4A = new RadioButton("A");
        radioButton4B = new RadioButton("B");
        radioButton4C = new RadioButton("C");
        radioButton4D = new RadioButton("D");

        group5 = new ToggleGroup();
        radioButton5A = new RadioButton("A");
        radioButton5B = new RadioButton("B");
        radioButton5C = new RadioButton("C");
        radioButton5D = new RadioButton("D");

        group6 = new ToggleGroup();
        radioButton6A = new RadioButton("A");
        radioButton6B = new RadioButton("B");
        radioButton6C = new RadioButton("C");
        radioButton6D = new RadioButton("D");

        group7 = new ToggleGroup();
        radioButton7A = new RadioButton("A");
        radioButton7B = new RadioButton("B");
        radioButton7C = new RadioButton("C");
        radioButton7D = new RadioButton("D");

        group8 = new ToggleGroup();
        radioButton8A = new RadioButton("A");
        radioButton8B = new RadioButton("B");
        radioButton8C = new RadioButton("C");
        radioButton8D = new RadioButton("D");

        group9 = new ToggleGroup();
        radioButton9A = new RadioButton("A");
        radioButton9B = new RadioButton("B");
        radioButton9C = new RadioButton("C");
        radioButton9D = new RadioButton("D");

        group10 = new ToggleGroup();
        radioButton10A = new RadioButton("A");
        radioButton10B = new RadioButton("B");
        radioButton10C = new RadioButton("C");
        radioButton10D = new RadioButton("D");
    }

    @Override
    protected void createToggleButtons() {
        answer1Button = new ToggleButton();
        answer2Button = new ToggleButton();
        answer3Button = new ToggleButton();
        answer4Button = new ToggleButton();
        answer5Button = new ToggleButton();
        answer6Button = new ToggleButton();
        answer7Button = new ToggleButton();
        answer8Button = new ToggleButton();
        answer9Button = new ToggleButton();
        answer10Button = new ToggleButton();
        listening1Button = new ToggleButton();
        textbookButton = new ToggleButton();

        backButton = new ToggleButton();
        startButton = new ToggleButton();
        nextButton = new ToggleButton();
        homeButton = new ToggleButton();

        //Toggle button group
        ToggleGroup toggleGroup = new ToggleGroup();
        backButton.setToggleGroup(toggleGroup);
        startButton.setToggleGroup(toggleGroup);
        homeButton.setToggleGroup(toggleGroup);
        nextButton.setToggleGroup(toggleGroup);

        toggleShowResult = new JFXToggleButton();
        toggleShowResult.setText("Show results");
        toggleShowResult.setStyle("-jfx-toggle-color: #DAA520;");
        //Set the saving file option as default
        toggleShowResult.setSelected(false);

        resizeButton = new ToggleButton();
    }

    @Override
    protected void createImageViews() {
        ImageView view;
        view = createImageView("resources/BACK_64.png");

        //Setting the size of the button
        backButton.setPrefSize(70, 30);
        //Setting a graphic to the button
        backButton.setGraphic(view);

        view = createImageView("resources/MUSIC_64.png");

        //Setting the size of the button
        startButton.setPrefSize(70, 30);
        //Setting a graphic to the button
        startButton.setGraphic(view);

        view = createImageView("resources/NEXT_64.png");

        //Setting the size of the button
        nextButton.setPrefSize(70, 30);
        //Setting a graphic to the button
        nextButton.setGraphic(view);

        view = createImageView("resources/HOME_64.png");

        //Setting the size of the button
        homeButton.setPrefSize(70, 30);
        //Setting a graphic to the button
        homeButton.setGraphic(view);

        ImageView view1;
        view1 = createImageView("resources/LIGHTBULBO_64.png");
        view1.setFitWidth(25);
        view1.setFitHeight(25);

        //Setting a graphic to the button
        answer1Button.setGraphic(view1);

        ImageView view2;
        view2 = createImageView("resources/LIGHTBULBO_64.png");
        view2.setFitWidth(25);
        view2.setFitHeight(25);

        //Setting a graphic to the button
        answer2Button.setGraphic(view2);

        ImageView view3;
        view3 = createImageView("resources/LIGHTBULBO_64.png");
        view3.setFitWidth(25);
        view3.setFitHeight(25);

        //Setting a graphic to the button
        answer3Button.setGraphic(view3);

        ImageView view4;
        view4 = createImageView("resources/LIGHTBULBO_64.png");
        view4.setFitWidth(25);
        view4.setFitHeight(25);

        //Setting a graphic to the button
        answer4Button.setGraphic(view4);

        ImageView view5;
        view5 = createImageView("resources/LIGHTBULBO_64.png");
        view5.setFitWidth(25);
        view5.setFitHeight(25);

        //Setting a graphic to the button
        answer5Button.setGraphic(view5);

        ImageView view6;
        view6 = createImageView("resources/LIGHTBULBO_64.png");
        view6.setFitWidth(25);
        view6.setFitHeight(25);

        //Setting a graphic to the button
        answer6Button.setGraphic(view6);

        ImageView view7;
        view7 = createImageView("resources/LIGHTBULBO_64.png");
        view7.setFitWidth(25);
        view7.setFitHeight(25);

        //Setting a graphic to the button
        answer7Button.setGraphic(view7);

        ImageView view8;
        view8 = createImageView("resources/LIGHTBULBO_64.png");
        view8.setFitWidth(25);
        view8.setFitHeight(25);

        //Setting a graphic to the button
        answer8Button.setGraphic(view8);

        ImageView view9;
        view9 = createImageView("resources/LIGHTBULBO_64.png");
        view9.setFitWidth(25);
        view9.setFitHeight(25);

        //Setting a graphic to the button
        answer9Button.setGraphic(view9);

        ImageView view10;
        view10 = createImageView("resources/LIGHTBULBO_64.png");
        view10.setFitWidth(25);
        view10.setFitHeight(25);

        //Setting a graphic to the button
        answer10Button.setGraphic(view10);

        ImageView view11;
        view11 = createImageView("resources/HEADPHONE2_64.png");
        view11.setFitWidth(70);
        view11.setFitHeight(30);
        //Setting a graphic to the button
        listening1Button.setGraphic(view11);

        ImageView view12;
        view12 = createImageView("resources/BOOK3_96.png");
        view12.setFitWidth(25);
        view12.setFitHeight(25);
        //Setting a graphic to the button
        textbookButton.setGraphic(view12);

        expand = createImageView("resources/EXPAND2_512.png");
        expand.setFitWidth(40);
        expand.setFitHeight(25);
        shrink = createImageView("resources/SHRINK2_512.png");
        shrink.setFitWidth(40);
        shrink.setFitHeight(25);
        resizeButton.setGraphic(expand);
    }

    @Override
    protected VBox createLayout() {
        buildUpPartListView();
        sourceComboBox = new ComboBox(sourceList);

        boolean isReady = false;
        historyRecordSortedMap = historyRecordFile.getHistoryRecordMap();
        for (Map.Entry rec: historyRecordSortedMap.entrySet() ) {
            HistoryRecord historyRecord = (HistoryRecord)rec.getValue();
            if(historyRecord.getBookMarked().matches("Y") &&
                    historyRecord.getPart().matches(parts.get(0).getPart())) {
                String itemNumber = historyRecord.getItemNumber();
                for(int i = 0; i < parts.size(); ++i) {
                    if(parts.get(i).getItemNum().equals(itemNumber)) {
                        this.numberOfRow = i;
                    }
                }
                previousSourceId = parts.get(numberOfRow).getSource();
                sourceComboBox.setValue(parts.get(numberOfRow).getSource());
                isReady = true;
            }
        }

        sourceComboBox.setMaxHeight(30);
        sourceComboBox.setPrefHeight(30);
        sourceComboBox.setPrefWidth(450);

        // Set to the 1st parts-record's source id
        if(!isReady)
            sourceComboBox.setValue(parts.get(0).getSource());

        result1 = new ImageView();
        result1.setFitHeight(20);
        result1.setFitWidth(20);
        final Image image1 = createImage("resources/CROSS_512.png");
        result1.setImage(image1);
        result1.setOpacity(0);
        resultDate1 = new Label();

        result2 = new ImageView();
        result2.setFitHeight(20);
        result2.setFitWidth(20);
        final Image image2 = createImage("resources/CROSS_512.png");
        result2.setImage(image2);
        result2.setOpacity(0);
        resultDate2 = new Label();

        result3 = new ImageView();
        result3.setFitHeight(20);
        result3.setFitWidth(20);
        final Image image3 = createImage("resources/CROSS_512.png");
        result3.setImage(image3);
        result3.setOpacity(0);
        resultDate3 = new Label();

        result4 = new ImageView();
        result4.setFitHeight(20);
        result4.setFitWidth(20);
        final Image image4 = createImage("resources/CROSS_512.png");
        result4.setImage(image4);
        result4.setOpacity(0);
        resultDate4 = new Label();

        result5 = new ImageView();
        result5.setFitHeight(20);
        result5.setFitWidth(20);
        final Image image5 = createImage("resources/CROSS_512.png");
        result5.setImage(image5);
        result5.setOpacity(0);
        resultDate5 = new Label();

        result6 = new ImageView();
        result6.setFitHeight(20);
        result6.setFitWidth(20);
        final Image image6 = createImage("resources/CROSS_512.png");
        result6.setImage(image6);
        result6.setOpacity(0);
        resultDate6 = new Label();

        result7 = new ImageView();
        result7.setFitHeight(20);
        result7.setFitWidth(20);
        final Image image7 = createImage("resources/CROSS_512.png");
        result7.setImage(image7);
        result7.setOpacity(0);
        resultDate7 = new Label();

        result8 = new ImageView();
        result8.setFitHeight(20);
        result8.setFitWidth(20);
        final Image image8 = createImage("resources/CROSS_512.png");
        result8.setImage(image8);
        result8.setOpacity(0);
        resultDate8 = new Label();

        result9 = new ImageView();
        result9.setFitHeight(20);
        result9.setFitWidth(20);
        final Image image9 = createImage("resources/CROSS_512.png");
        result9.setImage(image9);
        result9.setOpacity(0);
        resultDate9 = new Label();

        result10 = new ImageView();
        result10.setFitHeight(20);
        result10.setFitWidth(20);
        final Image image10 = createImage("resources/CROSS_512.png");
        result10.setImage(image10);
        result10.setOpacity(0);
        resultDate10 = new Label();

        review1 = new ImageView();
        review1.setFitHeight(40);
        review1.setFitWidth(40);
        Image image11 = createImage("resources/PIN2_512.png");
        review1.setImage(image11);
        review1.setOpacity(0.0);

        review2 = new ImageView();
        review2.setFitHeight(40);
        review2.setFitWidth(40);
        Image image12 = createImage("resources/PIN2_512.png");
        review2.setImage(image12);
        review2.setOpacity(0.0);

        review3 = new ImageView();
        review3.setFitHeight(40);
        review3.setFitWidth(40);
        Image image13 = createImage("resources/PIN2_512.png");
        review3.setImage(image13);
        review3.setOpacity(0.0);

        review4 = new ImageView();
        review4.setFitHeight(40);
        review4.setFitWidth(40);
        Image image14 = createImage("resources/PIN2_512.png");
        review4.setImage(image14);
        review4.setOpacity(0.0);

        review5 = new ImageView();
        review5.setFitHeight(40);
        review5.setFitWidth(40);
        Image image15 = createImage("resources/PIN2_512.png");
        review5.setImage(image15);
        review5.setOpacity(0.0);

        review6 = new ImageView();
        review6.setFitHeight(40);
        review6.setFitWidth(40);
        Image image16 = createImage("resources/PIN2_512.png");
        review6.setImage(image16);
        review6.setOpacity(0.0);

        review7 = new ImageView();
        review7.setFitHeight(40);
        review7.setFitWidth(40);
        Image image17 = createImage("resources/PIN2_512.png");
        review7.setImage(image17);
        review7.setOpacity(0.0);

        review8 = new ImageView();
        review8.setFitHeight(40);
        review8.setFitWidth(40);
        Image image18 = createImage("resources/PIN2_512.png");
        review8.setImage(image18);
        review8.setOpacity(0.0);

        review9 = new ImageView();
        review9.setFitHeight(40);
        review9.setFitWidth(40);
        Image image19 = createImage("resources/PIN2_512.png");
        review9.setImage(image19);
        review9.setOpacity(0.0);

        review10 = new ImageView();
        review10.setFitHeight(40);
        review10.setFitWidth(40);
        Image image20 = createImage("resources/PIN2_512.png");
        review10.setImage(image20);
        review10.setOpacity(0.0);

        setupQuestions(this.numberOfRow);
        rebuildTextsAndButtons(numberOfRow);
        restoreRadioButtons(numberOfRow, numberOfRow+1, numberOfRow+2,
                numberOfRow+3, numberOfRow+4, numberOfRow+5,
                numberOfRow+6, numberOfRow+7, numberOfRow+8,
                numberOfRow+9);

        bookMark = new ImageView();
        bookMark.setFitHeight(50);
        bookMark.setFitWidth(50);

        String part = this.parts.get(0).getPart();
        final Image image;
        image = createImage("resources/BOOKMARK3_512.png");

        bookMark.setImage(image);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(bookMark, sourceComboBox);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(hBox);
        return vBox;
    }

    protected HBox createBottomLayout() {
        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(backButton, nextButton, listening1Button, homeButton, toggleShowResult);
        hBox5.setSpacing(5);
        hBox5.setAlignment(Pos.TOP_LEFT);
        hBox5.setPadding(new Insets(10, 10, 10, 10));
        return hBox5;
    }

    protected ScrollPane createCenterLayout() {

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(radioButton1A, radioButton1B, radioButton1C, radioButton1D ,answer1Button,
                                   textbookButton, result1, resultDate1, review1);
        hBox1.setSpacing(20);
        hBox1.setAlignment(Pos.TOP_LEFT);
        hBox1.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(radioButton2A, radioButton2B, radioButton2C, radioButton2D ,answer2Button,
                                   result2, resultDate2, review2);
        hBox2.setSpacing(20);
        hBox2.setAlignment(Pos.TOP_LEFT);
        hBox2.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox3 = new HBox();
        hBox3.getChildren().addAll(radioButton3A, radioButton3B, radioButton3C, radioButton3D ,answer3Button,
                                   result3, resultDate3, review3);
        hBox3.setSpacing(20);
        hBox3.setAlignment(Pos.TOP_LEFT);
        hBox3.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox4 = new HBox();
        hBox4.getChildren().addAll(radioButton4A, radioButton4B, radioButton4C, radioButton4D,answer4Button,
                                   result4, resultDate4, review4);
        hBox4.setSpacing(20);
        hBox4.setAlignment(Pos.TOP_LEFT);
        hBox4.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox5 = new HBox();
        hBox5.getChildren().addAll(radioButton5A, radioButton5B, radioButton5C, radioButton5D ,answer5Button,
                                   result5, resultDate5, review5);
        hBox5.setSpacing(20);
        hBox5.setAlignment(Pos.TOP_LEFT);
        hBox5.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox6 = new HBox();
        hBox6.getChildren().addAll(radioButton6A, radioButton6B, radioButton6C, radioButton6D ,answer6Button,
                result6, resultDate6, review6);
        hBox6.setSpacing(20);
        hBox6.setAlignment(Pos.TOP_LEFT);
        hBox6.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox7 = new HBox();
        hBox7.getChildren().addAll(radioButton7A, radioButton7B, radioButton7C, radioButton7D ,answer7Button,
                result7, resultDate7, review7);
        hBox7.setSpacing(20);
        hBox7.setAlignment(Pos.TOP_LEFT);
        hBox7.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox8 = new HBox();
        hBox8.getChildren().addAll(radioButton8A, radioButton8B, radioButton8C, radioButton8D ,answer8Button,
                result8, resultDate8, review8);
        hBox8.setSpacing(20);
        hBox8.setAlignment(Pos.TOP_LEFT);
        hBox8.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox9 = new HBox();
        hBox9.getChildren().addAll(radioButton9A, radioButton9B, radioButton9C, radioButton9D ,answer9Button,
                result9, resultDate9, review9);
        hBox9.setSpacing(20);
        hBox9.setAlignment(Pos.TOP_LEFT);
        hBox9.setPadding(new Insets(10, 10, 10, 10));

        HBox hBox10 = new HBox();
        hBox10.getChildren().addAll(radioButton10A, radioButton10B, radioButton10C, radioButton10D ,answer10Button,
                result10, resultDate10, review10);
        hBox10.setSpacing(20);
        hBox10.setAlignment(Pos.TOP_LEFT);
        hBox10.setPadding(new Insets(10, 10, 10, 10));

        questionPane1 = new StackPane();
        questionPane2 = new StackPane();
        questionPane3 = new StackPane();
        questionPane4 = new StackPane();
        questionPane5 = new StackPane();
        questionPane6 = new StackPane();
        questionPane7 = new StackPane();
        questionPane8 = new StackPane();
        questionPane9 = new StackPane();
        questionPane10 = new StackPane();

        questionPane1.getChildren().addAll(question1, questionImgView1);
        questionPane2.getChildren().addAll(question2, questionImgView2);
        questionPane3.getChildren().addAll(question3, questionImgView3);
        questionPane4.getChildren().addAll(question4, questionImgView4);
        questionPane5.getChildren().addAll(question5, questionImgView5);
        questionPane6.getChildren().addAll(question6, questionImgView6);
        questionPane7.getChildren().addAll(question7, questionImgView7);
        questionPane8.getChildren().addAll(question8, questionImgView8);
        questionPane9.getChildren().addAll(question9, questionImgView9);
        questionPane10.getChildren().addAll(question10, questionImgView10);

        questionPane1.setAlignment(Pos.TOP_LEFT);
        questionPane2.setAlignment(Pos.TOP_LEFT);
        questionPane3.setAlignment(Pos.TOP_LEFT);
        questionPane4.setAlignment(Pos.TOP_LEFT);
        questionPane5.setAlignment(Pos.TOP_LEFT);
        questionPane6.setAlignment(Pos.TOP_LEFT);
        questionPane7.setAlignment(Pos.TOP_LEFT);
        questionPane8.setAlignment(Pos.TOP_LEFT);
        questionPane9.setAlignment(Pos.TOP_LEFT);
        questionPane10.setAlignment(Pos.TOP_LEFT);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.getChildren().addAll(questionPane1, hBox1, questionPane2, hBox2, questionPane3, hBox3,
                questionPane4, hBox4, questionPane5, hBox5, questionPane6, hBox6, questionPane7, hBox7,
                questionPane8, hBox8, questionPane9, hBox9, questionPane10, hBox10);

        vBox.setBackground(bg);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(bg);
        scrollPane.setPannable(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vBox);

        return scrollPane;
    }

    private void createScriptTabPane(int numberOfRow) {
        singleTab.setText(TAB_0);
        singleTab.setContent(createDocumentVBox(numberOfRow, parts.get(numberOfRow).getScriAttrib(),
                parts.get(numberOfRow).getScript(), parts.get(numberOfRow).getFilePath(), 820, 830));
        tabPaneLayout.getTabs().add(singleTab);

        doubleTab.setText(TAB_01);
        if(!parts.get(numberOfRow).getDialogAttrib().isEmpty()) {
            doubleTab.setContent(createDocumentVBox(numberOfRow, parts.get(numberOfRow).getDialogAttrib(),
                    parts.get(numberOfRow).getDialog(), parts.get(numberOfRow).getFilePath(), 820, 830));
        } else {
            doubleTab.setContent(new Label(TAB_01));
        }
        tabPaneLayout.getTabs().add(doubleTab);

        tripleTab.setText(TAB_02);
        if(!parts.get(numberOfRow).getTriplePassAttrib().isEmpty()) {
            tripleTab.setContent(createDocumentVBox(numberOfRow, parts.get(numberOfRow).getTriplePassAttrib(),
                    parts.get(numberOfRow).getTriplePassage(), parts.get(numberOfRow).getFilePath(), 820, 830));
        } else {
            tripleTab.setContent(new Label(TAB_02));
        }
        tabPaneLayout.getTabs().add(tripleTab);
        tabPaneLayout.getStylesheets().add("jfoenix-components.css");

        timerLayout.createTimerLayout();
        timerLayout.setItemNum(parts.get(numberOfRow).getItemNum());
        this.setUpTimeLimit(numberOfRow);

        // Only update the tab-contents for the different sourceID
        if (!parts.get(numberOfRow).getSource().equals(previousSourceId)) {
            timerLayout.processUpdateTimerRecord();
            timerLayout.performResetButtonAction();
        }

        timerLayout.updateTimeLimit();

        previousSourceId = parts.get(numberOfRow).getSource();
        menuButton = new MenuButton(MENU_HEADER);
        splitMenuButton = menuButton.createSplitMenuButton("resources/PIECHART_32.png",
                errataPieChart);

        Label filler = new Label();
        HBox hBox0 = new HBox();
        hBox0.setSpacing(15);
        hBox0.getChildren().addAll(resizeButton, examinationType, filler, timerLayout.getTimerLayout(), splitMenuButton);
        hBox0.setAlignment(Pos.TOP_RIGHT);

        rightLayout.setTop(hBox0);
        rightLayout.setCenter(tabPaneLayout);
    }

    private void enableDisableRadioAnswerButtons() {
        String questionNum = parts.get(numberOfRow).getQuestionNum();

        if (questionNum.matches("1")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(true);
            radioButton2B.setDisable(true);
            radioButton2C.setDisable(true);
            radioButton2D.setDisable(true);
            answer2Button.setDisable(true);

            radioButton3A.setDisable(true);
            radioButton3B.setDisable(true);
            radioButton3C.setDisable(true);
            radioButton3D.setDisable(true);
            answer3Button.setDisable(true);

            radioButton4A.setDisable(true);
            radioButton4B.setDisable(true);
            radioButton4C.setDisable(true);
            radioButton4D.setDisable(true);
            answer4Button.setDisable(true);

            radioButton5A.setDisable(true);
            radioButton5B.setDisable(true);
            radioButton5C.setDisable(true);
            radioButton5D.setDisable(true);
            answer5Button.setDisable(true);

            radioButton6A.setDisable(true);
            radioButton6B.setDisable(true);
            radioButton6C.setDisable(true);
            radioButton6D.setDisable(true);
            answer6Button.setDisable(true);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        } else if (questionNum.matches("2")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(true);
            radioButton3B.setDisable(true);
            radioButton3C.setDisable(true);
            radioButton3D.setDisable(true);
            answer3Button.setDisable(true);

            radioButton4A.setDisable(true);
            radioButton4B.setDisable(true);
            radioButton4C.setDisable(true);
            radioButton4D.setDisable(true);
            answer4Button.setDisable(true);

            radioButton5A.setDisable(true);
            radioButton5B.setDisable(true);
            radioButton5C.setDisable(true);
            radioButton5D.setDisable(true);
            answer5Button.setDisable(true);

            radioButton6A.setDisable(true);
            radioButton6B.setDisable(true);
            radioButton6C.setDisable(true);
            radioButton6D.setDisable(true);
            answer6Button.setDisable(true);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        } else if (questionNum.matches("3")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(true);
            radioButton4B.setDisable(true);
            radioButton4C.setDisable(true);
            radioButton4D.setDisable(true);
            answer4Button.setDisable(true);

            radioButton5A.setDisable(true);
            radioButton5B.setDisable(true);
            radioButton5C.setDisable(true);
            radioButton5D.setDisable(true);
            answer5Button.setDisable(true);

            radioButton6A.setDisable(true);
            radioButton6B.setDisable(true);
            radioButton6C.setDisable(true);
            radioButton6D.setDisable(true);
            answer6Button.setDisable(true);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        } else if (questionNum.matches("4")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(true);
            radioButton5B.setDisable(true);
            radioButton5C.setDisable(true);
            radioButton5D.setDisable(true);
            answer5Button.setDisable(true);

            radioButton6A.setDisable(true);
            radioButton6B.setDisable(true);
            radioButton6C.setDisable(true);
            radioButton6D.setDisable(true);
            answer6Button.setDisable(true);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        }  else if (questionNum.matches("5")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(true);
            radioButton6B.setDisable(true);
            radioButton6C.setDisable(true);
            radioButton6D.setDisable(true);
            answer6Button.setDisable(true);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        }  else if (questionNum.matches("6")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(false);
            radioButton6B.setDisable(false);
            radioButton6C.setDisable(false);
            radioButton6D.setDisable(false);
            answer6Button.setDisable(false);

            radioButton7A.setDisable(true);
            radioButton7B.setDisable(true);
            radioButton7C.setDisable(true);
            radioButton7D.setDisable(true);
            answer7Button.setDisable(true);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        }  else if (questionNum.matches("7")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(false);
            radioButton6B.setDisable(false);
            radioButton6C.setDisable(false);
            radioButton6D.setDisable(false);
            answer6Button.setDisable(false);

            radioButton7A.setDisable(false);
            radioButton7B.setDisable(false);
            radioButton7C.setDisable(false);
            radioButton7D.setDisable(false);
            answer7Button.setDisable(false);

            radioButton8A.setDisable(true);
            radioButton8B.setDisable(true);
            radioButton8C.setDisable(true);
            radioButton8D.setDisable(true);
            answer8Button.setDisable(true);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        }  else if (questionNum.matches("8")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(false);
            radioButton6B.setDisable(false);
            radioButton6C.setDisable(false);
            radioButton6D.setDisable(false);
            answer6Button.setDisable(false);

            radioButton7A.setDisable(false);
            radioButton7B.setDisable(false);
            radioButton7C.setDisable(false);
            radioButton7D.setDisable(false);
            answer7Button.setDisable(false);

            radioButton8A.setDisable(false);
            radioButton8B.setDisable(false);
            radioButton8C.setDisable(false);
            radioButton8D.setDisable(false);
            answer8Button.setDisable(false);

            radioButton9A.setDisable(true);
            radioButton9B.setDisable(true);
            radioButton9C.setDisable(true);
            radioButton9D.setDisable(true);
            answer9Button.setDisable(true);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        }  else if (questionNum.matches("9")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(false);
            radioButton6B.setDisable(false);
            radioButton6C.setDisable(false);
            radioButton6D.setDisable(false);
            answer6Button.setDisable(false);

            radioButton7A.setDisable(false);
            radioButton7B.setDisable(false);
            radioButton7C.setDisable(false);
            radioButton7D.setDisable(false);
            answer7Button.setDisable(false);

            radioButton8A.setDisable(false);
            radioButton8B.setDisable(false);
            radioButton8C.setDisable(false);
            radioButton8D.setDisable(false);
            answer8Button.setDisable(false);

            radioButton9A.setDisable(false);
            radioButton9B.setDisable(false);
            radioButton9C.setDisable(false);
            radioButton9D.setDisable(false);
            answer9Button.setDisable(false);

            radioButton10A.setDisable(true);
            radioButton10B.setDisable(true);
            radioButton10C.setDisable(true);
            radioButton10D.setDisable(true);
            answer10Button.setDisable(true);
        } else if (questionNum.matches("10")) {
            radioButton1A.setDisable(false);
            radioButton1B.setDisable(false);
            radioButton1C.setDisable(false);
            radioButton1D.setDisable(false);
            answer1Button.setDisable(false);

            radioButton2A.setDisable(false);
            radioButton2B.setDisable(false);
            radioButton2C.setDisable(false);
            radioButton2D.setDisable(false);
            answer2Button.setDisable(false);

            radioButton3A.setDisable(false);
            radioButton3B.setDisable(false);
            radioButton3C.setDisable(false);
            radioButton3D.setDisable(false);
            answer3Button.setDisable(false);

            radioButton4A.setDisable(false);
            radioButton4B.setDisable(false);
            radioButton4C.setDisable(false);
            radioButton4D.setDisable(false);
            answer4Button.setDisable(false);

            radioButton5A.setDisable(false);
            radioButton5B.setDisable(false);
            radioButton5C.setDisable(false);
            radioButton5D.setDisable(false);
            answer5Button.setDisable(false);

            radioButton6A.setDisable(false);
            radioButton6B.setDisable(false);
            radioButton6C.setDisable(false);
            radioButton6D.setDisable(false);
            answer6Button.setDisable(false);

            radioButton7A.setDisable(false);
            radioButton7B.setDisable(false);
            radioButton7C.setDisable(false);
            radioButton7D.setDisable(false);
            answer7Button.setDisable(false);

            radioButton8A.setDisable(false);
            radioButton8B.setDisable(false);
            radioButton8C.setDisable(false);
            radioButton8D.setDisable(false);
            answer8Button.setDisable(false);

            radioButton9A.setDisable(false);
            radioButton9B.setDisable(false);
            radioButton9C.setDisable(false);
            radioButton9D.setDisable(false);
            answer9Button.setDisable(false);

            radioButton10A.setDisable(false);
            radioButton10B.setDisable(false);
            radioButton10C.setDisable(false);
            radioButton10D.setDisable(false);
            answer10Button.setDisable(false);
        }
    }

    private void goToNextPage() {
        if(numberOfRow < lastRowNum - 4) {
            // Increase rowNum to move to the next question set
            numberOfRow = numberOfRow + Integer.valueOf(parts.get(numberOfRow).getQuestionNum());

            if (!sourceComboBox.getValue().equals(parts.get(numberOfRow).getSource())) {
                previousSourceId = sourceComboBox.getValue().toString();
                sourceComboBox.setValue((parts.get(numberOfRow).getSource()));
            }

            createScriptTabPane(numberOfRow);
            setupQuestions(numberOfRow);

            renewSourceCombo();
            rebuildTextsAndButtons(numberOfRow);
            restoreRadioButtons(numberOfRow, numberOfRow+1, numberOfRow+2,
                    numberOfRow+3, numberOfRow+4, numberOfRow+5,
                    numberOfRow+6, numberOfRow+7, numberOfRow+8,
                    numberOfRow+9);
            checkBookMark(numberOfRow);
            startSound("resources/bookPageFlip.mp3");
        }
    }

    private void goBackToPrevious() {
        // if not 1st Row item
        if(numberOfRow > 0) {
            int questionNumber = Integer.valueOf(parts.get(numberOfRow-1).getQuestionNum());
            // Decrease rowNum to move to the previous question set
            if (numberOfRow - questionNumber > 0) {
                numberOfRow = numberOfRow - questionNumber;
            } else {
                numberOfRow = 0;
            }

            if (!sourceComboBox.getValue().equals(parts.get(numberOfRow).getSource())) {
                previousSourceId = sourceComboBox.getValue().toString();
                setSkipRenewQuestions(true);
                sourceComboBox.setValue(parts.get(numberOfRow).getSource());
                setSkipRenewQuestions(false);
                timerLayout.processStopTimer(false);
            }

            createScriptTabPane(numberOfRow);
            setupQuestions(numberOfRow);

            renewSourceCombo();
            rebuildTextsAndButtons(numberOfRow);

            restoreRadioButtons(numberOfRow, numberOfRow+1, numberOfRow+2,
                    numberOfRow+3, numberOfRow+4, numberOfRow+5,
                    numberOfRow+6, numberOfRow+7, numberOfRow+8,
                    numberOfRow+9);
            checkBookMark(numberOfRow);
            startSound("resources/bookPageFlip.mp3");
        }
    }

    private void ProcessToGoToNextPage() {
        if(isNoWarningDialog) {
            warningDialog = new CustomDialog("WARNING", ARE_YOU_SURE_TO_CHANGE_THE_PAGE, "resources/WARNING_512.png");
            isNoWarningDialog = false;
        }
        if(numberOfRow < lastRowNum - 4) {
            // Increase rowNum to move to the next question set
            int curNumberOfRow = numberOfRow + Integer.valueOf(parts.get(numberOfRow).getQuestionNum());
            if (!sourceComboBox.getValue().equals(parts.get(curNumberOfRow).getSource())) {
                Optional<ButtonType> result = warningDialog.showAndAnimate();
                if (result.isPresent() && result.get() == CustomDialog.YES_BUTTON)
                    goToNextPage();
            } else {
                goToNextPage();
            }
        }
    }

    private void ProcessToGoBackToPrevious() {
        if(isNoWarningDialog) {
            warningDialog = new CustomDialog("WARNING", ARE_YOU_SURE_TO_CHANGE_THE_PAGE, "resources/WARNING_512.png");
            isNoWarningDialog = false;
        }
        if(numberOfRow > 0) {
            int questionNumber = Integer.valueOf(parts.get(numberOfRow-1).getQuestionNum());
            // Decrease rowNum to move to the previous question set
            int curNumberOfRow;
            if (numberOfRow - questionNumber > 0) {
                curNumberOfRow = numberOfRow - questionNumber;
            } else {
                curNumberOfRow = 0;
            }
            if (!sourceComboBox.getValue().equals(parts.get(curNumberOfRow).getSource())) {
                Optional<ButtonType> result = warningDialog.showAndAnimate();
                if (result.isPresent() && result.get() == CustomDialog.YES_BUTTON)
                    goBackToPrevious();
            } else {
                goBackToPrevious();
            }
        }
    }

    /**
     * Override the parent implemented method.
     * @return
     */
    protected Scene rebuildForm(){
        createToggleButtons();
        createRadioButtons();
        enableDisableRadioAnswerButtons();
        createImageViews();
        createGroupControls();

    //    setupQuestions(this.numberOfRow);
        border = new BorderPane();

        rightLayout = new BorderPane();
        tabPaneLayout = new JFXTabPane();
        singleTab = new Tab();
        doubleTab = new Tab();
        tripleTab = new Tab();

        examinationType = new Label();

        // Get the PART from the 1st record
        String part = this.parts.get(0).getPart();

        File file = null;
        if (part.matches("6"))
            file = new File("resources/A4paper.jpg");
        else
            file = new File("resources/A2paper.jpg");

        Image image = new Image(file.toURI().toString());

        BackgroundImage backgroundImage= new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);

        // Background creation
        bg = new Background(backgroundImage);

        topLeftLayout = createLayout();

        // set background
        border.setBackground(bg);
        border.setTop(topLeftLayout);
        border.setCenter(createCenterLayout());
        border.setBottom(createBottomLayout());

        assignActionEvent();

        setupDialogDocumentButtons(numberOfRow);
        setupLabelText(numberOfRow);

        timerLayout = new TimerLayout(Duration.seconds(0.0), true, parts.get(numberOfRow).getPart());

        createScriptTabPane(numberOfRow);

        //setUpTimeLimit(numberOfRow);

        //Creating a SplitPane
        splitPane = new SplitPane();
        splitPane.setDividerPositions(sliderWidth / (sliderWidth + 800)); // Initial divider position
        //Creating stack panes holding the ImageView objects
        StackPane stackPane1 = new StackPane(border);
        StackPane stackPane2 = new StackPane(rightLayout);
        stackPane2.setBackground(bg);

        //Adding the stackpanes to the splitpane
        splitPane.getItems().addAll(stackPane1, stackPane2);
        splitPane.setBackground(bg);

        //Setting anchor pane as the layout
        AnchorPane pane = new AnchorPane();
        AnchorPane.setTopAnchor(splitPane, 15.0);
        AnchorPane.setRightAnchor(splitPane, 5.0);
        AnchorPane.setBottomAnchor(splitPane, 15.0);
        AnchorPane.setLeftAnchor(splitPane, 5.0);
        pane.getChildren().addAll(splitPane);
        pane.setBackground(bg);

        scene = new Scene(pane, PART7_WIDTH, PART7_HEIGHT);
        scene.getStylesheets().add("Viper.css");

        // Assign the Swipe Left action
        scene.setOnSwipeLeft(e-> {
            ProcessToGoBackToPrevious();
        });

        // Assign the Swipe Right action
        scene.setOnSwipeRight(e-> {
            ProcessToGoToNextPage();
        });

//        ChangeListener<Number> changeListener = new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                splitPane.setDividerPositions(0.42);
//                if (m_stageShowing) {
//                    observable.removeListener(this);
//                }
//            }
//        };
//        splitPane.widthProperty().addListener(changeListener);
//        splitPane.heightProperty().addListener(changeListener);

        m_stageShowing = true;
        return scene;
    }

    @Override
    void rebuildTextsAndButtons(int row) {
        // Renew the question texts
        changeImageOrTextField(row);

        // Reset the background fill color on radio-buttons
        resetRadiobuttonBackground(radioButton1A);
        resetRadiobuttonBackground(radioButton1B);
        resetRadiobuttonBackground(radioButton1C);
        resetRadiobuttonBackground(radioButton1D);

        resetRadiobuttonBackground(radioButton2A);
        resetRadiobuttonBackground(radioButton2B);
        resetRadiobuttonBackground(radioButton2C);
        resetRadiobuttonBackground(radioButton2D);

        resetRadiobuttonBackground(radioButton3A);
        resetRadiobuttonBackground(radioButton3B);
        resetRadiobuttonBackground(radioButton3C);
        resetRadiobuttonBackground(radioButton3D);

        resetRadiobuttonBackground(radioButton4A);
        resetRadiobuttonBackground(radioButton4B);
        resetRadiobuttonBackground(radioButton4C);
        resetRadiobuttonBackground(radioButton4D);

        resetRadiobuttonBackground(radioButton5A);
        resetRadiobuttonBackground(radioButton5B);
        resetRadiobuttonBackground(radioButton5C);
        resetRadiobuttonBackground(radioButton5D);

        resetRadiobuttonBackground(radioButton6A);
        resetRadiobuttonBackground(radioButton6B);
        resetRadiobuttonBackground(radioButton6C);
        resetRadiobuttonBackground(radioButton6D);

        resetRadiobuttonBackground(radioButton7A);
        resetRadiobuttonBackground(radioButton7B);
        resetRadiobuttonBackground(radioButton7C);
        resetRadiobuttonBackground(radioButton7D);

        resetRadiobuttonBackground(radioButton8A);
        resetRadiobuttonBackground(radioButton8B);
        resetRadiobuttonBackground(radioButton8C);
        resetRadiobuttonBackground(radioButton8D);

        resetRadiobuttonBackground(radioButton9A);
        resetRadiobuttonBackground(radioButton9B);
        resetRadiobuttonBackground(radioButton9C);
        resetRadiobuttonBackground(radioButton9D);

        resetRadiobuttonBackground(radioButton10A);
        resetRadiobuttonBackground(radioButton10B);
        resetRadiobuttonBackground(radioButton10C);
        resetRadiobuttonBackground(radioButton10D);

        enableDisableRadioAnswerButtons();

        setupDialogDocumentButtons(row);
        setupTextbookButton(row);

        setupLabelText(row);
    }

    private void renewSourceCombo() {
        String source = sourceComboBox.getValue().toString();
        String record = parts.get(numberOfRow).getSource();

        if (!source.matches(record))
            sourceComboBox.setValue(record);
    }

    private void renewQuestionsMarkedRecord() {
        int itemNumber = -1;

        if(sourceHeadMap.get(sourceComboBox.getValue()) < lastRowNum) {
            itemNumber = sourceHeadMap.get(sourceComboBox.getValue());

            // Get the sorted itemNumber based on the head or tail
            if(isTail()) {
                if (sourceTailMap.containsKey(sourceComboBox.getValue()))
                    itemNumber = sourceTailMap.get(sourceComboBox.getValue());
            } else {
                if (sourceHeadMap.containsKey(sourceComboBox.getValue()))
                    itemNumber = sourceHeadMap.get(sourceComboBox.getValue());
            }
        }

        if (itemNumber != -1) {
            for (int i = 0; i < parts.size(); i++) {
                if (parts.get(i).getPartListNumber().equals(String.valueOf(itemNumber))) {
                    this.numberOfRow = i;
                }
            }

            if (isTail()) {
                // if not 1st Row item
                if(numberOfRow > 0) {
                    int previousQuestionNum = Integer.valueOf(parts.get(numberOfRow-1).getQuestionNum());
                    // Decrease rowNum to move to the previous question set
                    if (numberOfRow - previousQuestionNum > 0) {
                        numberOfRow = numberOfRow - previousQuestionNum;
                    } else {
                        numberOfRow = 0;
                    }
                }
            }
      //      setUpTimeLimit(numberOfRow);
            createScriptTabPane(numberOfRow);
            setupQuestions(numberOfRow);
            rebuildTextsAndButtons(numberOfRow);
            restoreRadioButtons(numberOfRow, numberOfRow+1, numberOfRow+2, numberOfRow+3,
                        numberOfRow+4, numberOfRow+5, numberOfRow+6, numberOfRow+7,
                      numberOfRow+8, numberOfRow+9);
            checkBookMark(numberOfRow);
        }
    }

    @Override
    protected void rebuildRadiobuttonBackground(RadioButton radioButton, boolean reassign) {

        if(this.isPart7showAnswer()) {
            if (reassign) {
                radioButton.setStyle("-fx-background-color:GREEN; -fx-background-radius: 15px; -fx-background-width: 2px;");
            } else {
                radioButton.setStyle("-fx-background-color:RED; -fx-background-radius: 15px; -fx-background-width: 2px;");
            }
        } else {
            radioButton.setStyle("-fx-background-color:null; -fx-background-radius: 15px; -fx-background-width: 2px;");
        }
        radioButton.setSelected(true);
    }

    private void restoreRadioButtons(int firstRow, int secondRow, int thirdRow, int fourthRow, int fifthRow,
                                     int sixthRow, int seventhRow, int eighthRow, int ninethRow, int tenthRow) {
        HashMap<String, ErrorRecord> record;

        record = errataPieChart.getRecord();

        //If there is any error record on HashMap
        if (!record.isEmpty()) {
            boolean result1 = false;
            boolean result2 = false;
            boolean result3 = false;
            boolean result4 = false;
            boolean result5 = false;
            boolean result6 = false;
            boolean result7 = false;
            boolean result8 = false;
            boolean result9 = false;
            boolean result10 = false;
            String answer1 = null;
            String answer2 = null;
            String answer3 = null;
            String answer4 = null;
            String answer5 = null;
            String answer6 = null;
            String answer7 = null;
            String answer8 = null;
            String answer9 = null;
            String answer10 = null;

            if (record.containsKey(parts.get(firstRow).getItemNum())) {
                answer1 = record.get(parts.get(firstRow).getItemNum()).getAnswer();
                result1 = record.get(parts.get(firstRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(secondRow).getItemNum())) {
                answer2 = record.get(parts.get(secondRow).getItemNum()).getAnswer();
                result2 = record.get(parts.get(secondRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(thirdRow).getItemNum())) {
                answer3 = record.get(parts.get(thirdRow).getItemNum()).getAnswer();
                result3 = record.get(parts.get(thirdRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(fourthRow).getItemNum())) {
                answer4 = record.get(parts.get(fourthRow).getItemNum()).getAnswer();
                result4 = record.get(parts.get(fourthRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(fifthRow).getItemNum())) {
                answer5 = record.get(parts.get(fifthRow).getItemNum()).getAnswer();
                result5 = record.get(parts.get(fifthRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(sixthRow).getItemNum())) {
                answer6 = record.get(parts.get(sixthRow).getItemNum()).getAnswer();
                result6 = record.get(parts.get(sixthRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(seventhRow).getItemNum())) {
                answer7 = record.get(parts.get(seventhRow).getItemNum()).getAnswer();
                result7 = record.get(parts.get(seventhRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(eighthRow).getItemNum())) {
                answer8 = record.get(parts.get(eighthRow).getItemNum()).getAnswer();
                result8 = record.get(parts.get(eighthRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(ninethRow).getItemNum())) {
                answer9 = record.get(parts.get(ninethRow).getItemNum()).getAnswer();
                result9 = record.get(parts.get(ninethRow).getItemNum()).isResult();
            }

            if (record.containsKey(parts.get(tenthRow).getItemNum())) {
                answer10 = record.get(parts.get(tenthRow).getItemNum()).getAnswer();
                result10 = record.get(parts.get(tenthRow).getItemNum()).isResult();
            }

            if (answer1 != null) {
                if (answer1.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton1A, result1);
                } else if (answer1.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton1B, result1);
                } else if (answer1.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton1C, result1);
                } else if (answer1.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton1D, result1);
                }
            }

            if (answer2 != null) {
                if (answer2.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton2A, result2);
                } else if (answer2.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton2B, result2);
                } else if (answer2.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton2C, result2);
                } else if (answer2.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton2D, result2);
                }
            }

            if (answer3 != null) {
                if (answer3.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton3A, result3);
                } else if (answer3.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton3B, result3);
                } else if (answer3.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton3C, result3);
                } else if (answer3.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton3D, result3);
                }
            }

            if (answer4 != null) {
                if (answer4.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton4A, result4);
                } else if (answer4.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton4B, result4);
                } else if (answer4.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton4C, result4);
                } else if (answer4.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton4D, result4);
                }
            }

            if (answer5 != null) {
                if (answer5.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton5A, result5);
                } else if (answer5.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton5B, result5);
                } else if (answer5.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton5C, result5);
                } else if (answer5.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton5D, result5);
                }
            }

            if (answer6 != null) {
                if (answer6.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton6A, result6);
                } else if (answer6.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton6B, result6);
                } else if (answer6.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton6C, result6);
                } else if (answer6.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton6D, result6);
                }
            }

            if (answer7 != null) {
                if (answer7.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton7A, result7);
                } else if (answer7.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton7B, result7);
                } else if (answer7.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton7C, result7);
                } else if (answer7.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton7D, result7);
                }
            }

            if (answer8 != null) {
                if (answer8.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton8A, result8);
                } else if (answer8.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton8B, result8);
                } else if (answer8.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton8C, result8);
                } else if (answer8.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton8D, result8);
                }
            }

            if (answer9 != null) {
                if (answer9.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton9A, result9);
                } else if (answer9.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton9B, result9);
                } else if (answer9.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton9C, result9);
                } else if (answer9.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton9D, result9);
                }
            }

            if (answer10 != null) {
                if (answer10.matches("A")) {
                    rebuildRadiobuttonBackground(radioButton10A, result10);
                } else if (answer10.matches("B")) {
                    rebuildRadiobuttonBackground(radioButton10B, result10);
                } else if (answer10.matches("C")) {
                    rebuildRadiobuttonBackground(radioButton10C, result10);
                } else if (answer10.matches("D")) {
                    rebuildRadiobuttonBackground(radioButton10D, result10);
                }
            }

            // call to enable/disable dialogButton
            setupDialogDocumentButtons(firstRow);

            // Restore the previous question texts
            question1.setText(questionText1);
            question2.setText(questionText2);
            question3.setText(questionText3);
            question4.setText(questionText4);
            question5.setText(questionText5);
            question6.setText(questionText6);
            question7.setText(questionText7);
            question8.setText(questionText8);
            question9.setText(questionText9);
            question10.setText(questionText10);

            setupLabelText(firstRow);

        }
    }

    private void setupDialogDocumentButtons(int row) {
        if(parts.get(row).getAudioFile().isEmpty())
            listening1Button.setDisable(true);
        else
            listening1Button.setDisable(false);
    }

    private void setupLabelText(int row) {
        BackgroundFill bf;
        Background bk;

        examinationType.setPadding(new Insets(5));
        examinationType.setFont(new Font("Arial", 18));
        examinationType.setTextFill(Color.WHITE);

        switch (parts.get(row).getCategory().trim()) {
            case "single":
                bf = new BackgroundFill(Color.PURPLE,
                        new CornerRadii(5) , Insets.EMPTY);
                bk = new Background(bf);
                examinationType.setBackground(bk);
                examinationType.setText(parts.get(row).getCategory().toString());
                break;
            case "double":
                bf = new BackgroundFill(Color.rgb(50,205,50),
                        new CornerRadii(5) , Insets.EMPTY);
                bk = new Background(bf);
                examinationType.setBackground(bk);
                examinationType.setText(parts.get(row).getCategory().toString());
                break;
            case "triple":
                bf = new BackgroundFill(Color.rgb(0,206,209),
                        new CornerRadii(5) , Insets.EMPTY);
                bk = new Background(bf);
                examinationType.setBackground(bk);
                examinationType.setText(parts.get(row).getCategory().toString());
                break;
            default:
                bf = new BackgroundFill(null, null, Insets.EMPTY);
                bk = new Background(bf);
                examinationType.setBackground(bk);
                examinationType.setText("");
        }
    }

    @Override
    protected void setupQuestions(int row) {
        String result = parts.get(row).getResult();
        String resultDate = parts.get(row).getResultDate();
        String questionNum = parts.get(row).getQuestionNum();

        this.setQuestionText1(parts.get(row).getQuestion());
        this.setAnswer1(parts.get(row).getAnswer());
        switchResultImage(result, resultDate, result1, resultDate1, review1);

        String review = reviewManagement.returnReviewRecord(parts.get(row).getItemNum());
        if(review.matches("Mark")) {
            review1.setOpacity(1.0);
        } else {
            review1.setOpacity(0);;
        }

        // Set the questions for 2 items
        if (questionNum.matches("1")) {
            this.setQuestionText2("");
            this.question2.setOpacity(0);
            this.setAnswer2("");

            this.questionImgView2.setImage(null);
            this.questionImgView2.setOpacity(0);
            result2.setOpacity(0);
            resultDate2.setText("");
            review2.setOpacity(0);

        } else {
            result = parts.get(row + 1).getResult();
            resultDate = parts.get(row + 1).getResultDate();

            this.setQuestionText2(parts.get(row + 1).getQuestion());
            this.setAnswer2(parts.get(row + 1).getAnswer());
            switchResultImage(result, resultDate, result2, resultDate2, review2);

            review = reviewManagement.returnReviewRecord(parts.get(row + 1).getItemNum());
            if(review.matches("Mark")) {
                review2.setOpacity(1.0);
            } else {
                review2.setOpacity(0);
            }
        }

        // Set the questions for 3 items
        switch (questionNum) {
            case "1": case "2":
                this.setQuestionText3("");
                this.question3.setOpacity(0);
                this.setAnswer3("");

                this.questionImgView3.setImage(null);
                this.questionImgView3.setOpacity(0);

                result3.setOpacity(0);
                resultDate3.setText("");
                review3.setOpacity(0);
                break;
            case "3": case "4": case "5": case "6": case "7": case "8": case "9": case "10":
                result = parts.get(row + 2).getResult();
                resultDate = parts.get(row + 2).getResultDate();
                switchResultImage(result, resultDate, result3, resultDate3, review3);

                review = reviewManagement.returnReviewRecord(parts.get(row + 2).getItemNum());
                if(review.matches("Mark")) {
                    review3.setOpacity(1.0);
                } else {
                    review3.setOpacity(0);
                }

                this.setQuestionText3(parts.get(row + 2).getQuestion());
                this.setAnswer3(parts.get(row + 2).getAnswer());
                break;
        }

        // Set the questions for 4 items
        switch (questionNum) {
            case "1": case "2": case "3":
                this.setQuestionText4("");
                this.question4.setOpacity(0);
                this.setAnswer4("");
                this.questionImgView4.setImage(null);
                this.questionImgView4.setOpacity(0);

                result4.setOpacity(0);
                resultDate4.setText("");
                review4.setOpacity(0);
                break;
            case "4": case "5": case "6": case "7": case "8": case "9": case "10":
                result = parts.get(row + 3).getResult();
                resultDate = parts.get(row + 3).getResultDate();
                switchResultImage(result, resultDate, result4, resultDate4, review4);

                review = reviewManagement.returnReviewRecord(parts.get(row + 3).getItemNum());
                if(review.matches("Mark")) {
                    review4.setOpacity(1.0);
                } else {
                    review4.setOpacity(0);
                }

                this.setQuestionText4(parts.get(row + 3).getQuestion());
                this.setAnswer4(parts.get(row + 3).getAnswer());
                break;
        }

        // Set the questions for 5 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4":
                this.setQuestionText5("");
                this.question5.setOpacity(0);
                this.setAnswer5("");
                this.questionImgView5.setImage(null);
                this.questionImgView5.setOpacity(0);

                result5.setOpacity(0);
                resultDate5.setText("");
                review5.setOpacity(0);
                break;
            case "5": case "6": case "7": case "8": case "9": case "10":
                result = parts.get(row + 4).getResult();
                resultDate = parts.get(row + 4).getResultDate();
                switchResultImage(result, resultDate, result5, resultDate5, review5);

                review = reviewManagement.returnReviewRecord(parts.get(row + 4).getItemNum());
                if(review.matches("Mark")) {
                    review5.setOpacity(1.0);
                } else {
                    review5.setOpacity(0);
                }

                this.setQuestionText5(parts.get(row + 4).getQuestion());
                this.setAnswer5(parts.get(row + 4).getAnswer());
                break;
        }

        // Set the questions for 6 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4": case "5":
                this.setQuestionText6("");
                this.question6.setOpacity(0);
                this.setAnswer6("");
                this.questionImgView6.setImage(null);
                this.questionImgView6.setOpacity(0);

                result6.setOpacity(0);
                resultDate6.setText("");
                review6.setOpacity(0);
                break;
            case "6": case "7": case "8": case "9": case "10":
                result = parts.get(row + 5).getResult();
                resultDate = parts.get(row + 5).getResultDate();
                switchResultImage(result, resultDate, result6, resultDate6, review6);

                review = reviewManagement.returnReviewRecord(parts.get(row + 5).getItemNum());
                if(review.matches("Mark")) {
                    review6.setOpacity(1.0);
                } else {
                    review6.setOpacity(0);
                }

                this.setQuestionText6(parts.get(row + 5).getQuestion());
                this.setAnswer6(parts.get(row + 5).getAnswer());
                break;
        }

        // Set the questions for 7 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4": case "5": case "6":
                this.setQuestionText7("");
                this.question7.setOpacity(0);
                this.setAnswer7("");
                this.questionImgView7.setImage(null);
                this.questionImgView7.setOpacity(0);

                result7.setOpacity(0);
                resultDate7.setText("");
                review7.setOpacity(0);
                break;
            case "7": case "8": case "9": case "10":
                result = parts.get(row + 6).getResult();
                resultDate = parts.get(row + 6).getResultDate();
                switchResultImage(result, resultDate, result7, resultDate7, review7);

                review = reviewManagement.returnReviewRecord(parts.get(row + 6).getItemNum());
                if(review.matches("Mark")) {
                    review7.setOpacity(1.0);
                } else {
                    review7.setOpacity(0);
                }

                this.setQuestionText7(parts.get(row + 6).getQuestion());
                this.setAnswer7(parts.get(row + 6).getAnswer());
                break;
        }

        // Set the questions for 8 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4": case "5": case "6": case "7":
                this.setQuestionText8("");
                this.question8.setOpacity(0);
                this.setAnswer8("");
                this.questionImgView8.setImage(null);
                this.questionImgView8.setOpacity(0);

                result8.setOpacity(0);
                resultDate8.setText("");
                review8.setOpacity(0);
                break;
            case "8": case "9": case "10":
                result = parts.get(row + 7).getResult();
                resultDate = parts.get(row + 7).getResultDate();
                switchResultImage(result, resultDate, result8, resultDate8, review8);

                review = reviewManagement.returnReviewRecord(parts.get(row + 7).getItemNum());
                if(review.matches("Mark")) {
                    review8.setOpacity(1.0);
                } else {
                    review8.setOpacity(0);
                }

                this.setQuestionText8(parts.get(row + 7).getQuestion());
                this.setAnswer8(parts.get(row + 7).getAnswer());
                break;
        }

        // Set the questions for 9 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8":
                this.setQuestionText9("");
                this.question9.setOpacity(0);
                this.setAnswer9("");
                this.questionImgView9.setImage(null);
                this.questionImgView9.setOpacity(0);

                result9.setOpacity(0);
                resultDate9.setText("");
                review9.setOpacity(0);
                break;
            case "9": case "10":
                result = parts.get(row + 8).getResult();
                resultDate = parts.get(row + 8).getResultDate();
                switchResultImage(result, resultDate, result9, resultDate9, review9);

                review = reviewManagement.returnReviewRecord(parts.get(row + 8).getItemNum());
                if(review.matches("Mark")) {
                    review9.setOpacity(1.0);
                } else {
                    review9.setOpacity(0);
                }

                this.setQuestionText9(parts.get(row +8).getQuestion());
                this.setAnswer9(parts.get(row + 8).getAnswer());
                break;
        }

        // Set the questions for 10 items
        switch (questionNum) {
            case "1": case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9":
                this.setQuestionText10("");
                this.question10.setOpacity(0);
                this.setAnswer10("");
                this.questionImgView10.setImage(null);
                this.questionImgView10.setOpacity(0);

                result10.setOpacity(0);
                resultDate10.setText("");
                review10.setOpacity(0);
                break;
            case "10":
                result = parts.get(row + 9).getResult();
                resultDate = parts.get(row + 9).getResultDate();
                switchResultImage(result, resultDate, result10, resultDate10, review10);

                review = reviewManagement.returnReviewRecord(parts.get(row + 9).getItemNum());
                if(review.matches("Mark")) {
                    review10.setOpacity(1.0);
                } else {
                    review10.setOpacity(0);
                }

                this.setQuestionText10(parts.get(row +9).getQuestion());
                this.setAnswer10(parts.get(row + 9).getAnswer());
                break;
        }

        // Create & assign the audio file name.
        String autoFileName = parts.get(row).getAudioFile();
        if (!autoFileName.isEmpty()) {
            String fileFullPath = parts.get(row).getFilePath()+autoFileName;
            if(!fileFullPath.isEmpty() || !fileFullPath.isBlank()) {
                File f = new File(fileFullPath);
                try {
                    this.setMediaPlayer(new Media(f.toURI().toString()));
                } catch (MediaException e) {
                    System.out.println("fileFullPath = " + fileFullPath);
                    e.printStackTrace();
                }
            }
        }
    }

    private void setUpTimeLimit(int row) {
        if (parts.get(row).getFullTime().matches("f1")) {
            if (part.matches("6"))
                assignTimeLimit(row, "part6time1");
            else
            assignTimeLimit(row, "part7time1");
        } else if (parts.get(row).getFullTime().matches("f2")) {
            if (part.matches("6"))
                assignTimeLimit(row, "part6time2");
            else
            assignTimeLimit(row, "part7time2");
        } else if (parts.get(row).getFullTime().matches("h1")) {
            assignTimeLimit(row, "part7half1");
        } else if (parts.get(row).getFullTime().matches("h2")) {
            assignTimeLimit(row, "part7half2");
        } else if (parts.get(row).getFullTime().matches("h3")) {
            assignTimeLimit(row, "part7half3");
        } else { // Not f1 or f2 specified
            switch (parts.get(row).getQuestionNum()) {
                case "1":
                    if (part.matches("6"))
                        timerLayout.setTimelimit(PART6_TIME);
                    else // PART 7
                        timerLayout.setTimelimit(PART7_TIME);
                    break;
                case "2":
                    if (part.matches("6"))
                        timerLayout.setTimelimit(PART6_TIME * 2);
                    else // PART 7
                        timerLayout.setTimelimit(PART7_TIME * 2);
                    break;
                case "3":
                    if (part.matches("6"))
                        timerLayout.setTimelimit(PART6_TIME * 3);
                    else // PART 7
                        timerLayout.setTimelimit(PART7_TIME * 3);
                    break;
                case "4":
                    if (part.matches("6"))
                        timerLayout.setTimelimit(PART6_TIME * 4);
                    else // PART 7
                        timerLayout.setTimelimit(PART7_TIME * 4);
                    break;
                case "5":
                    if (part.matches("6"))
                        timerLayout.setTimelimit(PART6_TIME * 5);
                    else // PART 7
                        timerLayout.setTimelimit(PART7_TIME * 5);
                    break;
            }
        }
    }

    private void toggleSidebar() {
        isExpanded= !isExpanded;
        double targetWidth = isExpanded ? 0.0 : sliderWidth;

        KeyValue keyValue = new KeyValue(splitPane.getDividers().get(0).positionProperty(), targetWidth / (targetWidth + 800));
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.8), keyValue);

        Timeline timeline = new Timeline(keyFrame);
        timeline.play();
    }

    private void updateViewImages(String name, String selection) {
        String itemNumber = null;
        String groupItemNumber;

        groupItemNumber= parts.get(numberOfRow).getItemNum();

        if(name.matches(QUESTION_1) || name.matches(QUESTION_IMG_VIEW_1)) {
            if (selection.matches("Mark"))
                review1.setOpacity(1.0);
            else
                review1.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow).getItemNum();

        } else if(name.matches(QUESTION_2) || name.matches(QUESTION_IMG_VIEW_2)) {
            if (selection.matches("Mark"))
                review2.setOpacity(1.0);
            else
                review2.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+1).getItemNum();

        } else if(name.matches(QUESTION_3) || name.matches(QUESTION_IMG_VIEW_3)) {
            if (selection.matches("Mark"))
                review3.setOpacity(1.0);
            else
                review3.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+2).getItemNum();

        } else if(name.matches(QUESTION_4) || name.matches(QUESTION_IMG_VIEW_4)) {
            if (selection.matches("Mark"))
                review4.setOpacity(1.0);
            else
                review4.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+3).getItemNum();

        } else if(name.matches(QUESTION_5) || name.matches(QUESTION_IMG_VIEW_5)) {
            if (selection.matches("Mark"))
                review5.setOpacity(1.0);
            else
                review5.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+4).getItemNum();

        } else if(name.matches(QUESTION_6) || name.matches(QUESTION_IMG_VIEW_6)) {
            if (selection.matches("Mark"))
                review6.setOpacity(1.0);
            else
                review6.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+5).getItemNum();

        } else if(name.matches(QUESTION_7) || name.matches(QUESTION_IMG_VIEW_7)) {
            if (selection.matches("Mark"))
                review7.setOpacity(1.0);
            else
                review7.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+6).getItemNum();

        } else if(name.matches(QUESTION_8) || name.matches(QUESTION_IMG_VIEW_8)) {
            if (selection.matches("Mark"))
                review8.setOpacity(1.0);
            else
                review8.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+7).getItemNum();

        } else if(name.matches(QUESTION_9) || name.matches(QUESTION_IMG_VIEW_9)) {
            if (selection.matches("Mark"))
                review9.setOpacity(1.0);
            else
                review9.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+8).getItemNum();

        } else if(name.matches(QUESTION_10) || name.matches(QUESTION_IMG_VIEW_10)) {
            if (selection.matches("Mark"))
                review10.setOpacity(1.0);
            else
                review10.setOpacity(0.0);
            itemNumber = parts.get(numberOfRow+9).getItemNum();
        }
        reviewManagement.updateResultRecord(itemNumber, groupItemNumber, parts.get(numberOfRow).getPart(), selection.toString(), false);
    }

}