-- 课程内容git仓库的根目录
insert into DRIP_PROPERTY_KEY (DBID,PROPERTY_KEY,GROUP_ID,PROPERTY_TYPE) VALUES (101, 'git.repo.root.course', 1, 1);
insert into DRIP_PROPERTY_VALUE_STRING (KEY_ID, PROPERTY_VALUE,I18n_ID) VALUES (101, '/mnt/drip_data/course/','zh_cn');

-- 即将开始的课程的提前通知时间
insert into DRIP_PROPERTY_KEY (DBID,PROPERTY_KEY,GROUP_ID,PROPERTY_TYPE) VALUES (102, 'alert.minite.before.lesson', 1, 2);
insert into DRIP_PROPERTY_VALUE_NUMBER (KEY_ID, PROPERTY_VALUE) VALUES (102, 15);