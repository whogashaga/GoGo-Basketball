package com.kerry.gogobasketball;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.kerry.gogobasketball.create_user.CreateUserFragment;
import com.kerry.gogobasketball.create_user.CreateUserPresenter;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.data.WaitingRoomSeats;
import com.kerry.gogobasketball.friends.FriendFragment;
import com.kerry.gogobasketball.friends.FriendPresenter;
import com.kerry.gogobasketball.home.HomeFragment;
import com.kerry.gogobasketball.home.HomePresenter;
import com.kerry.gogobasketball.home.item.Looking4RoomFragment;
import com.kerry.gogobasketball.home.item.Looking4RoomPresenter;
import com.kerry.gogobasketball.home.map.CourtsMapFragment;
import com.kerry.gogobasketball.home.map.CourtsMapPresenter;
import com.kerry.gogobasketball.login.LoginFragment;
import com.kerry.gogobasketball.login.LoginPresenter;
import com.kerry.gogobasketball.playing.player.PlayerGoingFragment;
import com.kerry.gogobasketball.playing.player.PlayerGoingPresenter;
import com.kerry.gogobasketball.playing.referee.RefereeGoingFragment;
import com.kerry.gogobasketball.playing.referee.RefereeGoingPresenter;
import com.kerry.gogobasketball.profile.ProfileFragment;
import com.kerry.gogobasketball.profile.ProfilePresenter;
import com.kerry.gogobasketball.rank.RankFragment;
import com.kerry.gogobasketball.rank.RankPresenter;
import com.kerry.gogobasketball.rank.player.RankPlayerFragment;
import com.kerry.gogobasketball.rank.player.RankPlayerPresenter;
import com.kerry.gogobasketball.rank.referee.RankRefereeFragment;
import com.kerry.gogobasketball.rank.referee.RankRefereePresenter;
import com.kerry.gogobasketball.result.player.PlayerResultFragment;
import com.kerry.gogobasketball.result.player.PlayerResultPresenter;
import com.kerry.gogobasketball.result.player.comment.CommentRefereeDialog;
import com.kerry.gogobasketball.result.player.comment.CommentRefereePresenter;
import com.kerry.gogobasketball.result.referee.RefereeResultFragment;
import com.kerry.gogobasketball.result.referee.RefereeResultPresenter;
import com.kerry.gogobasketball.util.ActivityUtils;
import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterFragment;
import com.kerry.gogobasketball.waiting4join.master.Waiting4JoinMasterPresenter;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlaveFragment;
import com.kerry.gogobasketball.waiting4join.slave.Waiting4JoinSlavePresenter;
import com.kerry.gogobasketball.want2create.Want2CreateRoomFragment;
import com.kerry.gogobasketball.want2create.Want2CreateRoomPresenter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MainMvpController {

    private final FragmentActivity mActivity;
    private MainPresenter mMainPresenter;

    private HomePresenter mHomePresenter;
    private LoginPresenter mLoginPresenter;
    private FriendPresenter mFriendPresenter;
    private RankPresenter mRankPresenter;
    private ProfilePresenter mProfilePresenter;

    private Want2CreateRoomPresenter mWant2CreateRoomPresenter;
    private Waiting4JoinMasterPresenter mWaiting4JoinMasterPresenter;
    private Waiting4JoinSlavePresenter mWaiting4JoinSlavePresenter;
    private RefereeGoingPresenter mRefereeGoingPresenter;
    private PlayerGoingPresenter mPlayerGoingPresenter;

    private RefereeResultPresenter mRefereeResultPresenter;
    private PlayerResultPresenter mPlayerResultPresenter;
    private CommentRefereePresenter mCommentRefereePresenter;

    private CreateUserPresenter mCreateUserPresenter;

    private Looking4RoomPresenter mLooking4RoomPresenter;
    private CourtsMapPresenter mCourtsMapPresenter;

    private RankRefereePresenter mRankRefereePresenter;
    private RankPlayerPresenter mRankPlayerPresenter;

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            HOME, FRIEND, RANK, PROFILE, WANT2CREATEROOM, WAITING4JOIN, GOING4REFEREE, GOING4PLAYER, RESULT4REFEREE, RESULT4PLAYER, LOGIN, CREATE1USER
    })
    public @interface FragmentType {
    }

    static final String HOME = "HOME";
    static final String FRIEND = "FRIEND";
    static final String RANK = "RANK";
    static final String PROFILE = "PROFILE";
    static final String WANT2CREATEROOM = "WANT2CREATEROOM";
    static final String WAITING4JOIN = "WAITING4JOIN";
    static final String GOING4REFEREE = "GOING4REFEREE";
    static final String GOING4PLAYER = "GOING4PLAYER";
    static final String RESULT4REFEREE = "RESULT4REFEREE";
    static final String RESULT4PLAYER = "RESULT4PLAYER";
    static final String LOGIN = "LOGIN";
    static final String CREATE1USER = "CREATE1USER";


    @Retention(RetentionPolicy.SOURCE)
    @StringDef({
            ROOMS, MAP, PLAYER, REFEREE
    })
    public @interface HomeItem { }
    public static final String ROOMS = "ROOMS";
    public static final String MAP = "MAP";

    public @interface RankItem { }
    public static final String PLAYER = "PLAYER";
    public static final String REFEREE = "REFEREE";


    private MainMvpController(@NonNull FragmentActivity activity) {
        mActivity = activity;
    }


    /**
     * Home Fragment View
     */
    void findOrCreateHomeView() {

        HomeFragment homeFragment = findOrCreateHomeFragment();

        if (mHomePresenter == null) {
            mHomePresenter = new HomePresenter(homeFragment);
            mMainPresenter.setHomePresenter(mHomePresenter);
            homeFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     * Login Fragment View
     */
    void findOrCreateLoginView() {

        LoginFragment loginFragment = findOrCreateLoginFragment();

        if (mLoginPresenter == null) {
            mLoginPresenter = new LoginPresenter(loginFragment);
            mMainPresenter.setLoginPresenter(mLoginPresenter);
            loginFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     * Friends View
     */
    void findOrCreateFriendsView() {

        FriendFragment friendsFragment = findOrCreateFriendFragment();

        if (mFriendPresenter == null) {
            mFriendPresenter = new FriendPresenter(friendsFragment);
            mMainPresenter.setFriendPresenter(mFriendPresenter);
            friendsFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     * Rank View
     */
    void findOrCreateRankView() {

        RankFragment rankFragment = findOrCreateRankFragment();

        if (mRankPresenter == null) {
            mRankPresenter = new RankPresenter(rankFragment);
            mMainPresenter.setRankPresenter(mRankPresenter);
            rankFragment.setPresenter(mMainPresenter);
        }
    }

    /**
     * Profile View
     */
    void findOrCreateProfileView() {

        ProfileFragment profileFragment = findOrCreateProfileFragment();

        if (mProfilePresenter == null) {
            mProfilePresenter = new ProfilePresenter(profileFragment);
            mMainPresenter.setProfilePresenter(mProfilePresenter);
            profileFragment.setPresenter(mMainPresenter);
        }
    }


    /**
     * LookingForRooms View
     *
     * @return HomeItemFragment: Rooms Fragment
     */
    Looking4RoomFragment findOrCreateLookingForRoomView() {

        Looking4RoomFragment fragment = findOrCreateRoomsItemFragment(ROOMS);

        mLooking4RoomPresenter = new Looking4RoomPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setLookingForRoomPresenter(mLooking4RoomPresenter);

        return fragment;
    }

    /**
     * MAP View
     *
     * @return HomeItemFragment: Map Fragment
     */
    CourtsMapFragment findOrCreateMapView() {

        CourtsMapFragment fragment = findOrCreateMapItemFragment(MAP);

        mCourtsMapPresenter = new CourtsMapPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setCourtsMapPresenter(mCourtsMapPresenter);

        return fragment;
    }

    /**
     * RankPlayer View
     *
     * @return RankItemFragment: RankPlayer Fragment
     */
    RankPlayerFragment findOrCreateRankPlayerView() {

        RankPlayerFragment fragment = findOrCreateRankPlayerItemFragment(PLAYER);

        mRankPlayerPresenter = new RankPlayerPresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setRankPlayerPresenter(mRankPlayerPresenter);

        return fragment;
    }

    /**
     * RankReferee View
     *
     * @return RankItemFragment: RankReferee Fragment
     */
    RankRefereeFragment findOrCreateRankRefereeView() {

        RankRefereeFragment fragment = findOrCreateRankRefereeItemFragment(REFEREE);

        mRankRefereePresenter = new RankRefereePresenter(fragment);
        fragment.setPresenter(mMainPresenter);
        mMainPresenter.setRankRefereePresenter(mRankRefereePresenter);

        return fragment;
    }

    /**
     * Want2CreateRoom View
     */
    void findOrCreateWant2CreateRoomView() {

        Want2CreateRoomFragment want2CreateRoomFragment = createWant2CreateRoomFragment();

        mWant2CreateRoomPresenter = new Want2CreateRoomPresenter(want2CreateRoomFragment);

        mMainPresenter.setWant2CreateRoomPresenter(mWant2CreateRoomPresenter);
        want2CreateRoomFragment.setPresenter(mMainPresenter);
    }

    /**
     * Waiting4JoinMaster View
     */
    void findOrCreateWaiting4JoinView(WaitingRoomInfo waitingRoomInfo, WaitingRoomSeats hostSeatsInfo, String roomDocId) {

        Waiting4JoinMasterFragment waiting4JoinFragment = createWaiting4JoinFragment();

        mWaiting4JoinMasterPresenter = new Waiting4JoinMasterPresenter(waiting4JoinFragment);
        mWaiting4JoinMasterPresenter.getRoomInfoFromWant2Create(waitingRoomInfo, hostSeatsInfo, roomDocId);

        mMainPresenter.setWaiting4JoinPresenter(mWaiting4JoinMasterPresenter);
        waiting4JoinFragment.setPresenter(mMainPresenter);
    }

    /**
     * Waiting4JoinSlave View
     */
    void findOrCreateWaiting4JoinSlaveView(WaitingRoomInfo waitingRoomInfo) {

        Waiting4JoinSlaveFragment waiting4JoinSlaveFragment = createWaiting4JoinSlaveFragment();

        mWaiting4JoinSlavePresenter = new Waiting4JoinSlavePresenter(waiting4JoinSlaveFragment);
        mWaiting4JoinSlavePresenter.getHostNameFromLooking4Room(waitingRoomInfo);

        mMainPresenter.setWaiting4JoinSlavePresenter(mWaiting4JoinSlavePresenter);
        waiting4JoinSlaveFragment.setPresenter(mMainPresenter);
    }

    /**
     * GamePlayingOfReferee View
     */
    void findOrCreateRefereeGoingView(String hostName) {

        RefereeGoingFragment gamePlayingOfRefereeFragment = createRefereeGoingFragment();

        mRefereeGoingPresenter = new RefereeGoingPresenter(gamePlayingOfRefereeFragment);
        mRefereeGoingPresenter.getHostNameFromWaitingJoin(hostName);

        mMainPresenter.setRefereeGoingPresenter(mRefereeGoingPresenter);
        gamePlayingOfRefereeFragment.setPresenter(mMainPresenter);
    }

    /**
     * GamePlayingOfPlayer View
     */
    void findOrCreatePlayerGoingView(String hostName, int nowSort) {

        PlayerGoingFragment playerGoingFragment = createPlayerGoingFragment();

        mPlayerGoingPresenter = new PlayerGoingPresenter(playerGoingFragment);
        mPlayerGoingPresenter.getHostNameFromWaitingJoinSlave(hostName, nowSort);

        mMainPresenter.setPlayerGoingPresenter(mPlayerGoingPresenter);
        playerGoingFragment.setPresenter(mMainPresenter);
    }

    /**
     * RefereeResult View
     */
    void findOrCreateRefereeResultView(String hostName) {
        RefereeResultFragment refereeResultFragment = createRefereeResultFragment();

        mRefereeResultPresenter = new RefereeResultPresenter(refereeResultFragment);
        mRefereeResultPresenter.getHostNameFromRefereeGoing(hostName);

        mMainPresenter.setRefereeResultPresenter(mRefereeResultPresenter);
        refereeResultFragment.setPresenter(mMainPresenter);
    }

    /**
     * PlayerResult View
     */
    void findOrCreatePlayerResultView(String hostName, int nowSort) {

        PlayerResultFragment playerResultFragment = createPlayerResultFragment();

        mPlayerResultPresenter = new PlayerResultPresenter(playerResultFragment);
        mPlayerResultPresenter.getHostNameFromPlayerGoing(hostName, nowSort);

        mMainPresenter.setPlayerResultPresenter(mPlayerResultPresenter);
        playerResultFragment.setPresenter(mMainPresenter);
    }


    /**
     *  CreateUser View
     */
    void findOrCreateCreateUserView(String userFbId) {

        CreateUserFragment createUserFragment = createCreateUserFragment();

        mCreateUserPresenter = new CreateUserPresenter(createUserFragment);
        mCreateUserPresenter.getUserIniInfoFromLogin(userFbId);

        mMainPresenter.setCreateUserPresenter(mCreateUserPresenter);
        createUserFragment.setPresenter(mMainPresenter);
    }

    /**
     * CommentRefereeDialog View
     */
    void findOrCreateCommentRefereeView(String refereeName) {

        CommentRefereeDialog dialog =
                (CommentRefereeDialog) getFragmentManager().findFragmentByTag(Constants.COMMENT);

        if (dialog == null) {

            dialog = new CommentRefereeDialog();
            mCommentRefereePresenter = new CommentRefereePresenter(dialog);
            mMainPresenter.setCommentRefereePresenter(mCommentRefereePresenter);
            mCommentRefereePresenter.getRefereeNameFromResult(refereeName);
            dialog.setPresenter(mMainPresenter);
            dialog.show(getFragmentManager(), Constants.COMMENT);

        } else if (!dialog.isAdded()) {
//            mWant2CommentPresenter.setAdd2CartProductData(product);
            dialog.show(getFragmentManager(), Constants.COMMENT);
        }
    }

    /* ------------------------------------------------------------------------------------------ */


    /**
     * Home Fragment
     *
     * @return HomeFragment
     */
    @NonNull
    private HomeFragment findOrCreateHomeFragment() {

        HomeFragment homeFragment =
                (HomeFragment) getFragmentManager().findFragmentByTag(HOME);
        if (homeFragment == null) {
            // Create the fragment
            homeFragment = HomeFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), homeFragment, HOME);

        return homeFragment;
    }

    /**
     * Friend Fragment
     *
     * @return FriendFragment
     */
    @NonNull
    private FriendFragment findOrCreateFriendFragment() {

        FriendFragment friendsFragment =
                (FriendFragment) getFragmentManager().findFragmentByTag(FRIEND);
        if (friendsFragment == null) {
            // Create the fragment
            friendsFragment = FriendFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), friendsFragment, FRIEND);

        return friendsFragment;
    }

    /**
     * Rank Fragment
     *
     * @return RankFragment
     */
    @NonNull
    private RankFragment findOrCreateRankFragment() {

        RankFragment rankFragment =
                (RankFragment) getFragmentManager().findFragmentByTag(RANK);
        if (rankFragment == null) {
            // Create the fragment
            rankFragment = RankFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), rankFragment, RANK);

        return rankFragment;
    }

    /**
     * Profile Fragment
     *
     * @return ProfileFragment
     */
    @NonNull
    private ProfileFragment findOrCreateProfileFragment() {

        ProfileFragment profileFragment =
                (ProfileFragment) getFragmentManager().findFragmentByTag(PROFILE);
        if (profileFragment == null) {
            // Create the fragment
            profileFragment = ProfileFragment.newInstance();
        }

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), profileFragment, PROFILE);

        return profileFragment;
    }

    /**
     * Home Item Fragment: Map
     *
     * @param itemType: @HomeItem
     * @return Looking4RoomFragment
     */
    @NonNull
    private Looking4RoomFragment findOrCreateRoomsItemFragment(@HomeItem String itemType) {

        Looking4RoomFragment fragment =
                (Looking4RoomFragment) (getFragmentManager().findFragmentByTag(HOME))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = Looking4RoomFragment.newInstance();
        }

        return fragment;
    }


    /**
     * Home Item Fragment: Map
     *
     * @param itemType: @HomeItem
     * @return CourtsMapFragment
     */
    @NonNull
    private CourtsMapFragment findOrCreateMapItemFragment(@HomeItem String itemType) {

        CourtsMapFragment fragment =
                (CourtsMapFragment) (getFragmentManager().findFragmentByTag(HOME))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = CourtsMapFragment.newInstance();
        }

        return fragment;
    }

    /**
     * Home Item Fragment: Player
     *
     * @param itemType: @RankItem
     * @return RankPlayerFragment
     */
    @NonNull
    private RankPlayerFragment findOrCreateRankPlayerItemFragment(@RankItem String itemType) {

        RankPlayerFragment fragment =
                (RankPlayerFragment) (getFragmentManager().findFragmentByTag(RANK))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = RankPlayerFragment.newInstance();
        }

        return fragment;
    }

    /**
     * Home Item Fragment: Referee
     *
     * @param itemType: @RankItem
     * @return RankRefereeFragment
     */
    @NonNull
    private RankRefereeFragment findOrCreateRankRefereeItemFragment(@RankItem String itemType) {

        RankRefereeFragment fragment =
                (RankRefereeFragment) (getFragmentManager().findFragmentByTag(RANK))
                        .getChildFragmentManager().findFragmentByTag(itemType);
        if (fragment == null) {
            // Create the fragment
            fragment = RankRefereeFragment.newInstance();
        }

        return fragment;
    }

    /**
     * Want2CreateRoom Fragment
     *
     * @return FaqFragment
     */
    @NonNull
    private Want2CreateRoomFragment createWant2CreateRoomFragment() {

        Want2CreateRoomFragment want2CreateRoomFragment = Want2CreateRoomFragment.newInstance();

        ActivityUtils.addFragmentByTag(
                getFragmentManager(), want2CreateRoomFragment, WANT2CREATEROOM);

        return want2CreateRoomFragment;
    }

    /**
     * Waiting4JoinMaster Fragment
     *
     * @return Waiting4JoinMasterFragment
     */
    @NonNull
    private Waiting4JoinMasterFragment createWaiting4JoinFragment() {

        Waiting4JoinMasterFragment waiting4JoinFragmentFragment = Waiting4JoinMasterFragment.newInstance();

        ActivityUtils.addFragmentByTag(
                getFragmentManager(), waiting4JoinFragmentFragment, WAITING4JOIN);

        return waiting4JoinFragmentFragment;
    }

    /**
     * Waiting4JoinSlave Fragment
     *
     * @return Waiting4JoinSlaveFragment
     */
    @NonNull
    private Waiting4JoinSlaveFragment createWaiting4JoinSlaveFragment() {

        Waiting4JoinSlaveFragment waiting4JoinSlaveFragment = Waiting4JoinSlaveFragment.newInstance();

        ActivityUtils.addFragmentByTag(
                getFragmentManager(), waiting4JoinSlaveFragment, WAITING4JOIN);

        return waiting4JoinSlaveFragment;
    }

    /**
     * RefereeGoing Fragment
     *
     * @return RefereeGoingFragment
     */
    @NonNull
    private RefereeGoingFragment createRefereeGoingFragment() {

        RefereeGoingFragment gamePlayingFragmentOfReferee = RefereeGoingFragment.newInstance();

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), gamePlayingFragmentOfReferee, GOING4REFEREE);

        return gamePlayingFragmentOfReferee;
    }


    /**
     * PlayerGoing Fragment
     *
     * @return PlayerGoingFragment
     */
    @NonNull
    private PlayerGoingFragment createPlayerGoingFragment() {

        PlayerGoingFragment gamePlayingFragmentOfPlayer = PlayerGoingFragment.newInstance();

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), gamePlayingFragmentOfPlayer, GOING4PLAYER);

        return gamePlayingFragmentOfPlayer;
    }

    /**
     * RefereeResult Fragment
     *
     * @return RefereeResultFragment
     */
    @NonNull
    private RefereeResultFragment createRefereeResultFragment() {

        RefereeResultFragment refereeResultFragment = RefereeResultFragment.newInstance();

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), refereeResultFragment, RESULT4REFEREE);

        return refereeResultFragment;
    }

    /**
     * PlayerResult Fragment
     *
     * @return PlayerResultFragment
     */
    @NonNull
    private PlayerResultFragment createPlayerResultFragment() {

        PlayerResultFragment playerResultFragment = PlayerResultFragment.newInstance();

        ActivityUtils.showOrAddFragmentByTag(
                getFragmentManager(), playerResultFragment, RESULT4PLAYER);

        return playerResultFragment;
    }

    /**
     * Login Fragment
     *
     * @return HomeFragment
     */
    @NonNull
    private LoginFragment findOrCreateLoginFragment() {

        LoginFragment loginFragment = LoginFragment.newInstance();

        ActivityUtils.addFragmentByTag(
                getFragmentManager(), loginFragment, LOGIN);

        return loginFragment;
    }

    /**
     * CreateUser Fragment
     *
     * @return CreateUserFragment
     */
    @NonNull
    private CreateUserFragment createCreateUserFragment() {

        CreateUserFragment createUserFragment = CreateUserFragment.newInstance();

        ActivityUtils.addFragmentByTagStateLoss(
                getFragmentManager(), createUserFragment, CREATE1USER);

        return createUserFragment;
    }

    /* ------------------------------------------------------------------------------------------ */

    /**
     * Creates a controller.
     *
     * @param activity the context activity
     * @return a MainMvpController
     */
    static MainMvpController create(@NonNull FragmentActivity activity) {
        checkNotNull(activity);
        MainMvpController mainMvpController = new MainMvpController(activity);
        mainMvpController.createMainPresenter();
        return mainMvpController;
    }

    /**
     * Create Main Presenter
     *
     * @return MainPresenter
     */
    private MainPresenter createMainPresenter() {
        mMainPresenter = new MainPresenter((MainActivity) mActivity);
        return mMainPresenter;
    }

    private FragmentManager getFragmentManager() {
        return mActivity.getSupportFragmentManager();
    }
}
