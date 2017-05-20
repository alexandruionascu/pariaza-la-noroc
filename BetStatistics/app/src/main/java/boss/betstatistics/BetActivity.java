package boss.betstatistics;

public class BetActivity extends BettingTips {

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    int getContentViewId() {
        return R.layout.bet_main;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_bets;
    }

}
