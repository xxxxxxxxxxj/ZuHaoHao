package com.haohao.zuhaohao.ui.module.user.contract;

import com.haohao.zuhaohao.ui.module.base.IABaseContract;

/**
 * 昵称修改
 * date：2017/12/4 14:58
 * author：Seraph
 *
 **/
public interface UpdateNickNameContract extends IABaseContract {

    interface View extends IBaseView {

        void setUserNickName(String usernick);

    }

    abstract class Presenter extends ABasePresenter<View> {

    }

}
