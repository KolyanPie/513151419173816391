package ru.edu.tetromino.visual;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Locale;

import ru.edu.tetromino.core.Figure;

public class MainScreen implements Screen {
    private Game game;

    private I18NBundle languageStrings = I18NBundle.createBundle(Gdx.files.internal("strings/strings"), new Locale("en"));
    private InputMultiplexer inputMultiplexer;
    private Stage stage;
    private Skin skin;
    private Stage backStage;
    private Stage mainMenuStage;
    private Stage campaignMenuStage;
    private Stage quickMenuStage;
    private Stage otherMenuStage;
    private Stage settingsMenuStage;

    public MainScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        mainMenuStageInitialize();
        campaignMenuStageInitialize();
        quickMenuStageInitialize();
        otherMenuStageInitialize();
        settingsMenuStageInitialize();
        backStage = new Stage(new FitViewport(400, 600));
        backStage();
        stage = mainMenuStage;
        inputMultiplexer = new InputMultiplexer();
        if (!inputMultiplexer.getProcessors().contains(backStage, false)) {
            inputMultiplexer.addProcessor(backStage);
        }
        if (!inputMultiplexer.getProcessors().contains(stage, false)) {
            inputMultiplexer.addProcessor(stage);
        }
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void backStage() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (!((int) (Math.random() * 6) == 0)) {
                    Figure figure = new Figure((byte) (Math.random() * 7), (byte) (Math.random() * 4));
                    figure.setBounds(i * 100 + (byte) (Math.random() * 30), j * 100 + (byte) (Math.random() * 30), 70);
                    backStage.addActor(figure);
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        backStage.act(delta);
        backStage.draw();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        backStage.dispose();
        skin.dispose();
        campaignMenuStage.dispose();
        quickMenuStage.dispose();
        otherMenuStage.dispose();
        settingsMenuStage.dispose();
    }

    private void mainMenuStageInitialize() {
        mainMenuStage = new Stage(new FitViewport(400, 600));

        Button quitButton = new TextButton(languageStrings.get("quit"), skin);
        quitButton.setBounds(100, 10, 200, 40);
        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        Button settingButton = new TextButton(languageStrings.get("settings"), skin);
        settingButton.setBounds(100, 360, 200, 40);
        settingButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeMenu(settingsMenuStage);
            }
        });
        Button otherButton = new TextButton(languageStrings.get("other"), skin);
        otherButton.setBounds(100, 410, 200, 40);
        otherButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeMenu(otherMenuStage);
            }
        });
        Button quickButton = new TextButton(languageStrings.get("quick"), skin);
        quickButton.setBounds(100, 460, 200, 40);
        quickButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeMenu(quickMenuStage);
            }
        });
        Button campaignButton = new TextButton(languageStrings.get("campaign"), skin);
        campaignButton.setBounds(100, 510, 200, 40);
        campaignButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeMenu(campaignMenuStage);
            }
        });
        ((TextButton) campaignButton).getLabel().setFontScale(0.3f);
        ((TextButton) quickButton).getLabel().setFontScale(0.3f);
        ((TextButton) otherButton).getLabel().setFontScale(0.3f);
        ((TextButton) settingButton).getLabel().setFontScale(0.3f);
        ((TextButton) quitButton).getLabel().setFontScale(0.3f);

        mainMenuStage.addActor(campaignButton);
        mainMenuStage.addActor(quickButton);
        mainMenuStage.addActor(otherButton);
        mainMenuStage.addActor(settingButton);
        mainMenuStage.addActor(quitButton);
    }

    private void campaignMenuStageInitialize() {
        campaignMenuStage = new Stage(new FitViewport(400, 600));

        Button backButton = new TextButton(languageStrings.get("back"), skin);
        backButton.setBounds(0, 10, 400, 80);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeMenu(mainMenuStage);
            }
        });

        Button temple3Button = new TextButton(languageStrings.get("temple") + " III", skin);
        temple3Button.setBounds(0, 210, 400, 80);
        Button temple2Button = new TextButton(languageStrings.get("temple") + " II", skin);
        temple2Button.setBounds(0, 310, 400, 80);
        Button temple1Button = new TextButton(languageStrings.get("temple") + " I", skin);
        temple1Button.setBounds(0, 410, 400, 80);
        Button tutorialButton = new TextButton(languageStrings.get("tutorial"), skin);
        tutorialButton.setBounds(0, 510, 400, 80);
        ((TextButton) tutorialButton).getLabel().setFontScale(0.5f);
        ((TextButton) temple1Button).getLabel().setFontScale(0.5f);
        ((TextButton) temple2Button).getLabel().setFontScale(0.5f);
        ((TextButton) temple3Button).getLabel().setFontScale(0.5f);
        ((TextButton) backButton).getLabel().setFontScale(0.5f);

        campaignMenuStage.addActor(tutorialButton);
        campaignMenuStage.addActor(temple1Button);
        campaignMenuStage.addActor(temple2Button);
        campaignMenuStage.addActor(temple3Button);
        campaignMenuStage.addActor(backButton);
    }

    private void quickMenuStageInitialize() {
        quickMenuStage = new Stage(new FitViewport(400, 600));
    }

    private void otherMenuStageInitialize() {
        otherMenuStage = new Stage(new FitViewport(400, 600));
    }

    private void settingsMenuStageInitialize() {
        settingsMenuStage = new Stage(new FitViewport(400, 600));
    }

    private void changeMenu(Stage menuStage) {
        inputMultiplexer.removeProcessor(stage);
        stage = menuStage;
        inputMultiplexer.addProcessor(stage);
    }
}
