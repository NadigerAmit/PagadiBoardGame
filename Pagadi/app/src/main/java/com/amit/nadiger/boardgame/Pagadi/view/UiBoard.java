package com.amit.nadiger.boardgame.Pagadi.view;

import android.app.Activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.amit.nadiger.boardgame.Pagadi.R;
import com.amit.nadiger.boardgame.Pagadi.etc.Constants;
import com.amit.nadiger.boardgame.Pagadi.etc.Utility;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Board.Piece.Piece;
import com.amit.nadiger.boardgame.Pagadi.model.Core.Game.Game;
import com.amit.nadiger.boardgame.Pagadi.viewmodel.CellViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.amit.nadiger.boardgame.Pagadi.etc.Constants.CELL_TYPE.INVALID;
import static com.amit.nadiger.boardgame.Pagadi.view.Appearance.context;

public class UiBoard extends View {
    private static final String TAG = "Board";
    int mSelectedSquare;
    float mCursorX, mCursorY;
    boolean mCursorVisible;
    protected int mX0, mY0, mSqSize;
    boolean mFlipped;
    boolean mOneTouchMoves;

    List<Move> moveHints;

    protected Paint mDarkPaint;
    protected Paint mBrightPaint;
    private Paint mSelectedSquarePaint;
    private Paint mCursorSquarePaint;
    private Paint mRestingCellPaint;
    private Paint mFinalDestinationPaint;
    private CellViewModel mCellViewModel = null;

    private ArrayList<Paint> mMoveMarkPaint;

    UiBoard(Context context, AttributeSet attrs) {
        super(context,attrs);
        Log.e(TAG," Default Board Constructor for Board View");
        //pos = new Position();
        android.app.Activity activity = (Activity) context;
        mSelectedSquare = -1;
        mCursorX = mCursorY = 0;
        mCursorVisible = false;
        mX0 = mY0 = mSqSize = 0;
        mFlipped = false;
        mOneTouchMoves = false;

        mDarkPaint = new Paint();
        mBrightPaint = new Paint();

        mRestingCellPaint = new Paint();
        mFinalDestinationPaint = new Paint();
        mSelectedSquarePaint = new Paint();
        mSelectedSquarePaint.setStyle(Paint.Style.STROKE);
        mSelectedSquarePaint.setAntiAlias(true);

        mCursorSquarePaint = new Paint();
        mCursorSquarePaint.setStyle(Paint.Style.STROKE);
        mCursorSquarePaint.setAntiAlias(true);

        mMoveMarkPaint = new ArrayList<Paint>();
        for (int i = 0; i < 6; i++) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setAntiAlias(true);
            mMoveMarkPaint.add(p);
        }
        setColors();

    }


    UiBoard(Context context, AttributeSet attrs, Game game) {
        super(context,attrs);
        Log.e(TAG,"Parameterized Board Constructor for Board View");
        //pos = new Position();
        android.app.Activity activity = (Activity) context;
        //mCellViewModel = ViewModelProviders.of(activity).get(CellViewModel.class);

    //    mGame = game;
        Log.e(TAG,"Game assigned ");
        mSelectedSquare = -1;
        mCursorX = mCursorY = 0;
        mCursorVisible = false;
        mX0 = mY0 = mSqSize = 0;
        mFlipped = false;
        mOneTouchMoves = false;
        mDarkPaint = new Paint();
        mBrightPaint = new Paint();

        mRestingCellPaint = new Paint();
        mFinalDestinationPaint = new Paint();
        mSelectedSquarePaint = new Paint();
        mSelectedSquarePaint.setStyle(Paint.Style.STROKE);
        mSelectedSquarePaint.setAntiAlias(true);

        mCursorSquarePaint = new Paint();
        mCursorSquarePaint.setStyle(Paint.Style.STROKE);
        mCursorSquarePaint.setAntiAlias(true);

        mMoveMarkPaint = new ArrayList<Paint>();
        for (int i = 0; i < 6; i++) {
            Paint p = new Paint();
            p.setStyle(Paint.Style.FILL);
            p.setAntiAlias(true);
            mMoveMarkPaint.add(p);
        }
        setColors();
    }

    public  void setViewModel(CellViewModel vm) {
        mCellViewModel = vm;
    }

    /** Configure the board's colors. */
    final void setColors() {
        mBrightPaint.setColor(Appearance.getColor(Appearance.BRIGHT_SQUARE));
        mDarkPaint.setColor(Appearance.getColor(Appearance.DARK_SQUARE));  // Amit: DARK_SQUARE to BRIGHT_SQUARE

        mSelectedSquarePaint.setColor(Appearance.getColor(Appearance.SELECTED_SQUARE));
        mCursorSquarePaint.setColor(Appearance.getColor(Appearance.CURSOR_SQUARE));

        int color = ContextCompat.getColor(context, R.color.colorAccent);
        mRestingCellPaint.setColor(color);
        mFinalDestinationPaint.setColor(Color.RED);

        for (int i = 0; i < 6; i++)
            mMoveMarkPaint.get(i).setColor(Appearance.getColor(Appearance.ARROW_0 + i));
        invalidate();
    }

    /**
     * Set the board to a given state.
     *
     * @param pos
     */
    /*
    final public void setPosition(Position pos) {
        if (!this.pos.equals(pos)) {
            this.pos = new Position(pos);
            invalidate();
        }
    }
    */

    /**
     * Set/clear the board mFlipped status.
     *
     * @param flipped
     */
    final public void setFlipped(boolean flipped) {
        if (this.mFlipped != flipped) {
            this.mFlipped = flipped;
            invalidate();
        }
    }

    /**
     * Set/clear the selected square.
     *
     * @param square
     *            The square to select, or -1 to clear selection.
     */
    final public void setSelection(int square) {
        if (square != mSelectedSquare) {
            mSelectedSquare = square;
            invalidate();
        }
    }

    protected int getWidth(int sqSize) {
        return sqSize * 5;
    }

    protected int getHeight(int sqSize) {
        return sqSize * 5;
    }

    protected int getSqSizeW(int width) {
        return width / 5;
    }

    protected int getSqSizeH(int height) {
        return height / 5;
    }

    protected int getMaxHeightPercentage() {
        return 75;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int sqSizeW = getSqSizeW(width);
        int sqSizeH = getSqSizeH(height);
        int sqSize = Math.min(sqSizeW, sqSizeH);
        Log.e(TAG," Before =>onMeasure for Board View width ="+width+" height ="+height+" sqSizeW ="+sqSizeW+" sqSizeH"+sqSizeH+
                "mSqSize "+sqSize);
        //Before =>onMeasure for Board View width =1080 height =1836 sqSizeW =216 sqSizeH 367mSqSize 216
        //Before =>onMeasure for Board View width =0 height =1836 sqSizeW =0 sqSizeH 367 mSqSize 0


        if (height > width) {
            int p = getMaxHeightPercentage();
            height = Math.min(getHeight(sqSize), height * p / 100);
        } else {
            width = Math.min(getWidth(sqSize), width * 65 / 100);
        }

        Log.e(TAG," After =>onMeasure for Board View width ="+width+" height ="+height+" sqSizeW ="+sqSizeW+" sqSizeH"+sqSizeH+
                " mSqSize ="+sqSize);
        //After =>onMeasure for Board View width =1080 height =1080 sqSizeW =216 sqSizeH 367 mSqSize =216
        //After =>onMeasure for Board View width =0 height =0 sqSizeW =0 sqSizeH 367 mSqSize =0

        setMeasuredDimension(width, height);
    }

    protected void computeOrigin(int width, int height) {
        mX0 = (width - mSqSize * 5) / 2;
        mY0 = (height - mSqSize * 8) / 1;
    }

    protected int getXFromCell(int sq) {
        return Utility.getX(sq);
    }

    protected int getYFromCell(int sq) {
        return Utility.getY(sq);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int width = getWidth();
        final int height = getHeight();
        mSqSize = Math.min(getSqSizeW(width), getSqSizeH(height));
        computeOrigin(width, height);
        Log.e(TAG,"Width = "+width+ " Height = "+height + "SqSize = "+ mSqSize);
        //Width = 1080 Height = 1080SqSize = 216
        // get the Game and Board and all the details.
        int pieceType = Constants.EMPTY;
        ArrayList<Integer> pieceTypeList = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                final int xCrd = getXCrd(x);
                final int yCrd = getYCrd(y);
                Paint paint ;
                if(mCellViewModel == null) {
                    Log.e(TAG,"mGame is null , returning !!s");
                    return;
                }
                int square = Utility.getSquare(x,y);
                final Constants.CELL_TYPE currentCellType = mCellViewModel.cellType(square);;

                if(currentCellType == INVALID) {
                    Log.e(TAG,"Current Cell is null x = "+ x +" y = "+y);
                    continue;
                }
                if(currentCellType == Constants.CELL_TYPE.RESTING_CELL) {
                    paint = mRestingCellPaint;
                } else if (currentCellType == Constants.CELL_TYPE.DESTINATION_CELL) {
                    paint = mFinalDestinationPaint;
                } else {
                    paint = Utility.darkSquare(x, y) ? mDarkPaint
                            : mBrightPaint;
                }
                canvas.drawRect(xCrd, yCrd, xCrd + mSqSize, yCrd + mSqSize, paint);

             //   Log.e(TAG,"OnDraw for Board View xCrd= "+xCrd+" yCrd = "+yCrd+" xCrd + mSqSize = " +
             //           " "+xCrd + mSqSize + " yCrd + mSqSize  "+yCrd + mSqSize);
             //   OnDraw for Board View xCrd= 0 yCrd = 864 xCrd + mSqSize =  0216 yCrd + mSqSize  864216

                // get the piece from the current cell

                 ArrayList<Integer> pieceTypeListList = mCellViewModel.getPieceTypesList(square);
                 if(pieceTypeListList != null) {
                     Log.e(TAG,"Sq = "+square+ " Piece Type "+pieceType);
                     drawPiece(canvas, xCrd + mSqSize / 2, yCrd + mSqSize / 2,pieceTypeListList);
                 } else {
                   //  Log.e(TAG," Piece is = NULL");
                 }
            }
        }
        drawExtraSquares(canvas);
        if (mSelectedSquare != -1) {
            int selX = getXFromCell(mSelectedSquare);
            int selY = getYFromCell(mSelectedSquare);
            mSelectedSquarePaint.setStrokeWidth(mSqSize / (float) 16);
            int x0 = getXCrd(selX);
            int y0 = getYCrd(selY);
            canvas.drawRect(x0, y0, x0 + mSqSize, y0 + mSqSize,
                    mSelectedSquarePaint);
        }

        if (mCursorVisible) {
            int x = Math.round(mCursorX);
            int y = Math.round(mCursorY);
            int x0 = getXCrd(x);
            int y0 = getYCrd(y);
            mCursorSquarePaint.setStrokeWidth(mSqSize / (float) 16);
            canvas.drawRect(x0, y0, x0 + mSqSize, y0 + mSqSize, mCursorSquarePaint);
        }
        drawMoveHints(canvas);
    }

    private final void drawMoveHints(Canvas canvas) {
        if (moveHints == null)
            return;
        float h = (float) (mSqSize / 2.0);
        float d = (float) (mSqSize / 8.0);
        double v = 35 * Math.PI / 180;
        double cosv = Math.cos(v);
        double sinv = Math.sin(v);
        double tanv = Math.tan(v);
        int n = Math.min(mMoveMarkPaint.size(), moveHints.size());
        for (int i = 0; i < n; i++) {
            Move m = moveHints.get(i);
            float x0 = getXCrd(Utility.getX(m.from)) + h;
            float y0 = getYCrd(Utility.getY(m.from)) + h;
            float x1 = getXCrd(Utility.getX(m.to)) + h;
            float y1 = getYCrd(Utility.getY(m.to)) + h;


            float x2 = (float) (Math.hypot(x1 - x0, y1 - y0) + d);
            float y2 = 0;
            float x3 = (float) (x2 - h * cosv);
            float y3 = (float) (y2 - h * sinv);
            float x4 = (float) (x3 - d * sinv);
            float y4 = (float) (y3 + d * cosv);
            float x5 = (float) (x4 + (-d / 2 - y4) / tanv);
            float y5 = (float) (-d / 2);
            float x6 = 0;
            float y6 = y5 / 2;
            Path path = new Path();
            path.moveTo(x2, y2);
            path.lineTo(x3, y3);
            // path.lineTo(x4, y4);
            path.lineTo(x5, y5);
            path.lineTo(x6, y6);
            path.lineTo(x6, -y6);
            path.lineTo(x5, -y5);
            // path.lineTo(x4, -y4);
            path.lineTo(x3, -y3);
            path.close();
            Matrix mtx = new Matrix();
            mtx.postRotate((float) (Math.atan2(y1 - y0, x1 - x0) * 180 / Math.PI));
            mtx.postTranslate(x0, y0);
            path.transform(mtx);
            Paint p = mMoveMarkPaint.get(i);
            canvas.drawPath(path, p);
        }
    }

    protected void drawExtraSquares(Canvas canvas) {
    }

    protected final void drawPiece(Canvas canvas, int xCrd, int yCrd,ArrayList<Integer> pieceTypeList) {
        Drawable dr = null;
        int numOfPiece = pieceTypeList.size();
        for(Integer p:pieceTypeList) {
            switch (p) {
                default:
                case Constants.EMPTY:
                    dr = null;        // don't do anything
                    break;
                case Constants.WKING:
                    dr = getContext().getResources().getDrawable(R.drawable.wk);
                    break;
                case Constants.WQUEEN:
                    dr = getContext().getResources().getDrawable(R.drawable.wq);
                    break;
                case Constants.BKING:
                    dr = getContext().getResources().getDrawable(R.drawable.bk);
                    break;
                case Constants.BQUEEN:
                    dr = getContext().getResources().getDrawable(R.drawable.bq);
                    break;
                case -1:

            }
            // TODO : Now all piece are overwritten , need logic to drwan them non overlapping based on total number.
            // TODO : Based on numOfPiece.
            if (dr != null) {
                int xOrigin = xCrd - (mSqSize / 2);
                int yOrigin = yCrd - (mSqSize / 2);
                Log.e(TAG, "Drawing piece type = " + p);
                // Todo , below code needs to be refactored to accomadate piceses dynamically
                xOrigin -= 25;
                dr.setBounds(xOrigin, yOrigin, xOrigin + mSqSize / 2, yOrigin + mSqSize / 2);
                dr.draw(canvas);
                // xOrigin += mSqSize/5;
            /*
            xOrigin += 50;
           // yOrigin +=mSqSize/6;
            dr.setBounds(xOrigin, yOrigin, xOrigin + mSqSize/2, yOrigin + mSqSize/2);
            dr.draw(canvas);
            //xOrigin += (mSqSize/4)-5;
            xOrigin += 50;
           // yOrigin +=mSqSize/4;
            dr.setBounds(xOrigin, yOrigin, xOrigin + mSqSize/2, yOrigin + mSqSize/2);
            dr.draw(canvas);
            //xOrigin += (mSqSize/3)-25;
            xOrigin += 50;
          //  yOrigin +=mSqSize/2;
            dr.setBounds(xOrigin, yOrigin, xOrigin + mSqSize/2, yOrigin + mSqSize/2);
            dr.draw(canvas);
            */
            }
        }
    }

    protected int getXCrd(int x) {
        return mX0 + mSqSize * (mFlipped ? 7 - x : x);
    }

    protected int getYCrd(int y) {
        return mY0 + mSqSize * (mFlipped ? y : 7 - y);
    }

    public int getXSq(int xCrd) {
        int t = (xCrd - mX0) / mSqSize;
        return mFlipped ? 7 - t : t;
    }

    public int getYSq(int yCrd) {
        int t = (yCrd - mY0) / mSqSize;
        return mFlipped ? t : 7 - t;
    }

    /**
     * Compute the square corresponding to the coordinates of a mouse event.
     *
     * @param evt
     *            Details about the mouse event.
     * @return The square corresponding to the mouse event, or -1 if outside
     *         board.
     */
    public int eventToSquare(MotionEvent evt) {
        int xCrd = (int) (evt.getX());
        int yCrd = (int) (evt.getY());

        int sq = -1;
        if (mSqSize > 0) {
            int x = getXSq(xCrd);
            int y = getYSq(yCrd);
            Log.e(TAG,"x = "+x + "  y = "+y);
            if ((x >= 0) && (x < 8) && (y >= 0) && (y < 8)) {
                sq = Utility.getSquare(x, y);
            }
        }
        return sq;
    }

    public int coOrdinateToSquare(int xCrd ,int yCrd) {

        int sq = -1;
        if (mSqSize > 0) {
            int x = getXSq(xCrd);
            int y = getYSq(yCrd);
            Log.e(TAG,"x = "+x + "  y = "+y);
            if ((x >= 0) && (x < 5) && (y >= 0) && (y < 5)) {
                sq = Utility.getSquare(x, y);
            }
        }
        return sq;
       // return Utility.transformCoOrdinateSquareToCells(sq);
    }

    final private int myColor(Piece piece) {
        if (piece != null) {
            return piece.getPieceType();
        }
        return -1;
    }

    Move mousePressed(int sq) {
        return null;
    }

    public static class OnTrackballListener {
        public void onTrackballEvent(MotionEvent event) {
        }
    }

    private OnTrackballListener otbl = null;

    public final void setOnTrackballListener(
            OnTrackballListener onTrackballListener) {
        otbl = onTrackballListener;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        if (otbl != null) {
            otbl.onTrackballEvent(event);
            return true;
        }
        return false;
    }

    protected int minValidY() {
        return 0;
    }

    protected int getSquare(int x, int y) {
        return Utility.getSquare(x, y);
    }

    public final Move handleTrackballEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                invalidate();
                if (mCursorVisible) {
                    int x = Math.round(mCursorX);
                    int y = Math.round(mCursorY);
                    mCursorX = x;
                    mCursorY = y;
                    int sq = getSquare(x, y);
                    return mousePressed(sq);
                }
                return null;
        }
        mCursorVisible = true;
        int c = mFlipped ? -1 : 1;
        mCursorX += c * event.getX();
        mCursorY -= c * event.getY();
        if (mCursorX < 0)
            mCursorX = 0;
        if (mCursorX > 7)
            mCursorX = 7;
        if (mCursorY < minValidY())
            mCursorY = minValidY();
        if (mCursorY > 7)
            mCursorY = 7;
        invalidate();
        return null;
    }

    public final void setMoveHints(List<Move> moveHints) {
        boolean equal = false;
        if ((this.moveHints == null) || (moveHints == null)) {
            equal = this.moveHints == moveHints;
        } else {
            equal = this.moveHints.equals(moveHints);
        }
        if (!equal) {
            this.moveHints = moveHints;
            invalidate();
        }
    }

}
