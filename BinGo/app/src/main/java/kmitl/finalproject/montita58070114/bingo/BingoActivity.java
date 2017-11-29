package kmitl.finalproject.montita58070114.bingo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BingoActivity extends AppCompatActivity {

    private TextView title, round, state, allRand;
    private HashMap<TextView, BlockNumber> blockViewMaps = new HashMap<>();
    private Button controlButton;

    private IGame gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingo);


        this.title = (TextView) findViewById(R.id.title);
        this.round = (TextView) findViewById(R.id.roundTextView);
        this.state = (TextView) findViewById(R.id.stateTextView);
        this.allRand = (TextView) findViewById(R.id.allRand);
        this.controlButton = (Button) findViewById(R.id.controlBtn);

        mapBlockViews();

        this.gameManager = new Game(new ArrayList<>(blockViewMaps.values()));
        this.controlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleControl();
            }
        });

        updateViews();
    }

    private void mapBlockViews() {
        for (int i = 1; i <= 9; i++) {
            final TextView blockView = (TextView) findViewById(getResources().getIdentifier("block" + i, "id", getPackageName()));
            final BlockNumber block = new BlockNumber(i);

            blockView.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String num = blockView.getText().toString();
                    if (!num.equals("")) {
                        block.setNumber(Integer.parseInt(num));
                    }

                    if (!block.isValidBlock()) {
                        blockView.setBackgroundColor(0xFFf99393);
                    } else {
                        if (block.getPos() % 2 != 0) {
                            blockView.setBackgroundColor(0xFFbababa);
                        } else {
                            blockView.setBackgroundColor(0xFFffffff);
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            blockViewMaps.put(blockView, block);
        }
    }

    private void handleControl() {
        if (!checkValidBlocks()) {
            return;
        }

        if (gameManager.getState().equals(GameState.NOT_PLAYING)) {
            gameManager.start();
            for (Map.Entry<TextView, BlockNumber> e : blockViewMaps.entrySet()) {
                TextView v = e.getKey();
                v.setEnabled(false);
                v.setTextColor(0xFF000000);
            }
        } else if (gameManager.getState().equals(GameState.PLAYING)) {
            gameManager.checkNext();
        } else {
            gameManager.reset();
            for (Map.Entry<TextView, BlockNumber> e : blockViewMaps.entrySet()) {
                Log.d("RESET", "handleControl: fired");
                TextView v = e.getKey();
                v.setEnabled(true);
            }
        }

        updateViews();
    }

    private boolean checkValidBlocks() {
        boolean flag = true;

        for (Map.Entry<TextView, BlockNumber> e : blockViewMaps.entrySet()) {
            TextView v = e.getKey();
            BlockNumber block = e.getValue();

            if (!block.isValidBlock()) {
                v.setBackgroundColor(0xFFf99393);
                flag = false;
            } else {
                if (block.getPos() % 2 != 0) {
                    v.setBackgroundColor(0xFFbababa);
                } else {
                    v.setBackgroundColor(0xFFffffff);
                }
            }
        }

        return flag;
    }

    private String CUR_ROUND_TEXT = "Current Round : ";

    private void updateViews() {
        if (gameManager.getState().equals(GameState.NOT_PLAYING)) {
            title.setText("Insert Number (0 - 99)");
            round.setText("Not start!");
            controlButton.setText("Start");
            state.setText("Prepare your number");
            allRand.setText("");
        } else if (gameManager.getState().equals(GameState.PLAYING)) {
            getMatchBlock();
            title.setText("Playing");
            round.setText(CUR_ROUND_TEXT + gameManager.getRound());
            controlButton.setText("Next Round");
            state.setText(gameManager.getLastRandNum() + " | Not match, continue.");
            allRand.setText(beautifyRandNums());
        } else if (gameManager.getState().equals(GameState.WIN)) {
            getWinPattern();
            title.setText("Finish");
            round.setText("END");
            controlButton.setText("New Game");
            state.setText("BINGO!!!!!");
            allRand.setText(beautifyRandNums());
        } else if (gameManager.getState().equals(GameState.LOSE)) {
            getMatchBlock(true);
            title.setText("Finish");
            round.setText("END");
            controlButton.setText("New Game");
            state.setText("Not Match, try again! :(");
            allRand.setText(beautifyRandNums());
        }
    }

    private String beautifyRandNums() {
        ArrayList<Integer> randNums = gameManager.getRandomNumbers();

        String nums = "[";
        int i = 0;
        for (int num : randNums) {
            if (i++ > 0) {
                nums += ", ";
            }
            nums += num;
        }
        nums += "]";

        return nums;
    }

    private void getWinPattern() {
        int[] pattern = gameManager.getWinPattern();

        for (Map.Entry<TextView, BlockNumber> e : blockViewMaps.entrySet()) {
            TextView v = e.getKey();
            BlockNumber block = e.getValue();

            boolean flag = false;

            for (int i : pattern) {
                if (block.getPos() == i) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                v.setBackgroundColor(0xFF51b1db);
            } else {
                if (block.getPos() % 2 != 0) {
                    v.setBackgroundColor(0xFFbababa);
                } else {
                    v.setBackgroundColor(0xFFffffff);
                }
            }
        }
    }

    private void getMatchBlock() {
        getMatchBlock(false);
    }

    private void getMatchBlock(boolean end) {
        for (Map.Entry<TextView, BlockNumber> e : blockViewMaps.entrySet()) {
            TextView v = e.getKey();
            BlockNumber block = e.getValue();

            if (block.getState().equals(BlockState.MATCH)) {
                v.setBackgroundColor(0xFF55c668);
            } else if (end) {
                v.setBackgroundColor(0xFFf99393);
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnShare:
                //onShareScreenshot();
                return true;
            default:
                return false;
        }
    }

    public void onShareScreenshot(View view) {
        Bitmap bitmap = ScreenShot.getScreenShot(view);
        File saveFilePath = ScreenShot.getMainDirectoryName(this);
        File file = ScreenShot.store(bitmap, "screenshot.jpg", saveFilePath);
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType("image/jpg");
        startActivity(Intent.createChooser(intent, "send image"));
    }


}
