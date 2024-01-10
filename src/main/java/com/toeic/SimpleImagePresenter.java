package com.toeic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class SimpleImagePresenter extends Application {

    // 現在表示しているページ番号
    private int pageIndex = 0;

    // シーングラフのルート要素
    private Group root;

    private FileVisitor fileVisitor;
    private FileInputStream input = null;
    private List<FileInputStream> fileInputStreams;
    private List<Path> fileList;

    @Override
    public void start(Stage stage) throws IOException {
        // ステージを透明にする
        //stage.initStyle(StageStyle.TRANSPARENT);

        String path = "src/main/resources/page";

        prepareFileList(path);

        root = new Group();
        Scene scene = new Scene(root, 800, 800);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();

        scene.setOnSwipeRight(e-> {
            try {
                // ページインデックスを進める
                // 最後までいったら最初に戻す
                pageIndex++;
                if (pageIndex >= fileList.size()) {
                    pageIndex = 0;
                }
                // マウスクリックされたら、次のページへ
                changePage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        scene.setOnSwipeLeft(e-> {
            try {
                // ページインデックスを進める
                // 最後までいったら最初に戻す
                pageIndex--;
                if (pageIndex < 0) {
                    pageIndex = fileList.size() - 1;
                }
                // マウスクリックされたら、次のページへ
                changePage();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        // 最初のページを表示する
        changePage();
    }

    private void prepareFileInputStream() {
        fileInputStreams = new ArrayList<>();
        try {
            for (Path item: fileList) {
                input = new FileInputStream(String.valueOf(item));
                fileInputStreams.add(input);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void prepareFileList(String path) {
        try {
            fileVisitor = new FileVisitor();
            Files.walkFileTree(Paths.get(path), fileVisitor);
            fileList = fileVisitor.getFiles();
        }
        catch (IOException e) {
            System.out.println("Error walking directory tree.");
        }
    }

    // ページを進める
    private void changePage() throws IOException {
        // 次のページをロードして、表示する
        try {
            input = new FileInputStream(fileList.get(pageIndex).toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(input);
        ImageView page = new ImageView(image);
        page.setPreserveRatio(true);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(780, 780);
        scrollPane.setContent(page);

        root.getChildren().add(scrollPane);

        // 前のページが存在している場合は、削除する
        if (root.getChildren().size() > 1) {
            root.getChildren().remove(0);
        }
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
