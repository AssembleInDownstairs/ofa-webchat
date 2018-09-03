insert into t_user(user_id,user_account,password,nickname,phone,province,city,gender,birthday,profile_photo,personal_profile,user_status,create_time) values
('user_id111','user_account111','password111','nickname111','phone111','001','001','01','2018-2-15 00:23:53','profile_photo111','personal_profile111','01','2018-2-15 00:23:53');

insert into t_user(user_id,user_account,password,nickname,phone,province,city,gender,birthday,profile_photo,personal_profile,user_status,create_time) values
('user_id222','user_account222','password222','nickname222','phone222','001','001','01','2018-2-15 00:23:53','profile_photo222','personal_profile222','01','2018-2-15 00:23:53');

insert into t_user(user_id,user_account,password,nickname,phone,province,city,gender,birthday,profile_photo,personal_profile,user_status,create_time) values
('user_id333','user_account333','password333','nickname333','phone333','001','001','01','2018-2-15 00:23:53','profile_photo333','personal_profile333','01','2018-2-15 00:23:53');


insert into t_contact(contact_record_id,user_id,contact_id,remark,add_time,last_contact_time) values
('contact_record_id111','user_id111','user_id222','remark111','2018-2-15 00:23:53','2018-2-15 00:23:53');

insert into t_contact(contact_record_id,user_id,contact_id,remark,add_time,last_contact_time) values
('contact_record_id222','user_id222','user_id111','remark222','2018-2-15 00:23:53','2018-2-15 00:23:53');

insert into t_contact(contact_record_id,user_id,contact_id,remark,add_time,last_contact_time) values
('contact_record_id333','user_id111','user_id333','remark111','2018-2-15 00:23:53',null);

insert into t_contact(contact_record_id,user_id,contact_id,remark,add_time,last_contact_time) values
('contact_record_id444','user_id333','user_id111','remark222','2018-2-15 00:23:53',null);


insert into t_group(group_id,group_code,group_name,group_image,administrator_id,create_time,create_by) values
('group_id111','group_code111','group_name111','group_image111','user_id111','2018-2-15 00:23:53','create_by111');

insert into t_group(group_id,group_code,group_name,group_image,administrator_id,create_time,create_by) values
('group_id222','group_code222','group_name222','group_image222','user_id222','2018-2-15 00:23:53','create_by222');


insert into t_group_menber(group_menber_record_id,group_id,user_id,user_group_nickname,add_time,last_contact_time) values
('group_menber_record_id111','group_id111','user_id111','user_group_nickname111','2018-2-15 00:23:53','2018-2-15 00:23:53');

insert into t_group_menber(group_menber_record_id,group_id,user_id,user_group_nickname,add_time,last_contact_time) values
('group_menber_record_id222','group_id111','user_id222','user_group_nickname222','2018-2-15 00:23:53','2018-2-16 00:23:53');

insert into t_group_menber(group_menber_record_id,group_id,user_id,user_group_nickname,add_time,last_contact_time) values
('group_menber_record_id333','group_id111','user_id333','user_group_nickname333','2018-2-15 00:23:53',null);



insert into t_chat_message(chat_message_id,user_id,chatter_id,message_type,message_content,time_of_send) values
('chat_message_id111','user_id111','user_id222','01','hello','2018-2-15 00:23:53');

insert into t_chat_message(chat_message_id,user_id,chatter_id,message_type,message_content,time_of_send) values
('chat_message_id222','user_id111','user_id222','01','hello2','2018-2-16 00:23:53');

insert into t_group_chat_message(group_chat_message_id,group_id,user_id,message_type,message_content,time_of_send) values
('group_chat_message_id111','group_id111','user_id111','01','hello','2018-2-15 00:23:53');

insert into t_group_chat_message(group_chat_message_id,group_id,user_id,message_type,message_content,time_of_send) values
('group_chat_message_id222','group_id111','user_id222','01','hello2','2018-2-16 00:23:53');