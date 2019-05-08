package com.kerry.gogobasketball.home.item.find1host;

import static com.google.common.base.Preconditions.checkNotNull;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kerry.gogobasketball.FireStoreHelper;
import com.kerry.gogobasketball.data.WaitingRoomInfo;
import com.kerry.gogobasketball.util.Constants;

import java.util.ArrayList;

public class FindHostPresenter implements FindHostContract.Presenter {

    private final FindHostContract.View mFindHostView;
    private String mHostId;


    public FindHostPresenter(@NonNull FindHostContract.View findHostView) {
        mFindHostView = checkNotNull(findHostView, "changeIdView cannot be null!");
        mFindHostView.setPresenter(this);
        mHostId = "";
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void showErrorToast(String message, boolean isShort) {

    }

    @Override
    public void onHostIdEditTextChange(CharSequence charSequence) {
        mHostId = charSequence.toString().trim();
    }

    @Override
    public void checkIfRoomExists(Activity activity) {
        FirebaseFirestore.getInstance()
                .collection(Constants.WAITING_ROOM)
                .whereEqualTo(Constants.HOST_NAME, mHostId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        Log.d(Constants.TAG, "checkIfUserIdExists task size = " + task.getResult().size());
                        if (task.isSuccessful()) {
                            if (task.getResult().size() == 0) {
                                mFindHostView.showFindNoHost();
                            } else {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    WaitingRoomInfo waitingRoomInfo = document.toObject(WaitingRoomInfo.class);
                                    // 只有一筆，跑 for 沒關係
                                    ArrayList<WaitingRoomInfo> list = new ArrayList<>();
                                    list.add(waitingRoomInfo);
                                    mFindHostView.showFindHostSuccessUi(list);
                                }
                            }
                        } else {
                            Log.w(Constants.TAG, "Error getting documents.", task.getException());
                        }
                    }
                }).addOnFailureListener(e -> {
            Log.w(Constants.TAG, "checkIfUserIdExists Error !");
        });
    }

    @Override
    public void updateRecyclerView(Activity activity) {

    }

    @Override
    public void getWaitingRoomFromFindHost(ArrayList<WaitingRoomInfo> list) {

    }

    @Override
    public void start() {

    }
}
