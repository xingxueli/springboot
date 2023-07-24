package com.company.project.core.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserContext {
    private UserContext() {
    }
    private static final ThreadLocal<UserSession> context = new InheritableThreadLocal<>();

    public static void setUser(String userId,String name) {
        context.set(UserSession.builder()
                        .userId(userId)
                        .name(name)
                .build());
    }
    public static void setUser(UserSession user) {
        context.set(user);
    }
    /**
     * 获取用户uid
     *
     * @return a {@link java.lang.String} object.
     */
    public static String getUid() {
        UserSession session = getSession();
        if(null == session){
            return null;
        }
        return session.getUserId();
    }


    /**
     * 当我们使用get方法的时候，即使未设置过值，它也会设置一个null值
     * 如果不移除，就会内存泄露
     */
    public static UserSession getSession(){
        UserSession userSession = context.get();
        if(userSession == null){
            context.remove();
        }
        return userSession;
    }

    /**
     * 清除当前线程内引用，防止内存泄漏
     */
    public static void removeUser() {
        context.remove();
    }
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSession {
        private String userId;
        private String name;
    }
}
