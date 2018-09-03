package webchat_gd.websocket;

import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import webchat_gd.dto.GroupMemberDto;
import webchat_gd.entity.Group;
import webchat_gd.entity.GroupMenber;
import webchat_gd.service.GroupService;
import webchat_gd.utils.SpringContextUtil;

/**
 * 聊天服务器类
 */
@Component
@ServerEndpoint(value = "/websocket")
public class ChatServer {

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	private static CopyOnWriteArraySet<ChatServer> webSocketSet = new CopyOnWriteArraySet<ChatServer>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	@OnOpen
	public void open(Session session) {
		this.session = session;
		webSocketSet.add(this);
		System.out.println("用户id：" + session.getId() + "加入websocket");
		System.out.println("--------------------");
	}

	@OnMessage
	public void getMessage(String message, Session session) {
		System.out.println("用户：" + session.getId() + " send message.......");
		// 把客户端的消息解析为JSON对象
		JsonObject json = new JsonParser().parse(message).getAsJsonObject();
		System.out.println("message:" + json.toString());
		String wsType = json.get("wsType").getAsString();

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("wsType", wsType);
		JsonArray userIds = new JsonArray();

		switch (wsType) {
		case "1":
			String id1 = json.get("userId").getAsString();
			userIds.add(id1);
			jsonObject.add("userIds", userIds);
			for (ChatServer chatServer : webSocketSet) {
				chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
			}
			System.out.println("发送给用户 " + id1 + " 信息: " + jsonObject.toString());
			break;
		case "2":
			String id2 = json.get("groupId").getAsString();
			Group group = new Group();
			group.setGroupId(id2);
			GroupService groupService = (GroupService) SpringContextUtil.getBean("groupServiceImpl");
			List<Group> selectResult = groupService.selectGroup(group);
			if (selectResult != null && selectResult.size() == 1) {
				String administratorId = selectResult.get(0).getAdministratorId();
				userIds.add(administratorId);
				jsonObject.add("userIds", userIds);
				for (ChatServer chatServer : webSocketSet) {
					chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
				}
				System.out.println("发送给用户 " + administratorId + " 信息: " + jsonObject.toString());
			}
			break;
		case "3":
			String id3 = json.get("chatterId").getAsString();
			userIds.add(id3);
			jsonObject.add("userIds", userIds);

			for (ChatServer chatServer : webSocketSet) {
				chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
			}
			System.out.println("发送给用户 " + id3 + " 信息: " + jsonObject.toString());
			break;
		case "4":
			String id4 = json.get("groupId").getAsString();
			GroupMenber groupMenber = new GroupMenber();
			groupMenber.setGroupId(id4);
			GroupService groupService4 = (GroupService) SpringContextUtil.getBean("groupServiceImpl");
			List<GroupMemberDto> groupMenbers = groupService4.selectGroupMenber(groupMenber);
			for (GroupMemberDto gm : groupMenbers) {
				userIds.add(gm.getUserId());
			}
			jsonObject.add("userIds", userIds);
			for (ChatServer chatServer : webSocketSet) {
				chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
			}
			System.out.println("发送给用户 " + userIds.toString() + " 信息: " + jsonObject.toString());
			break;
		case "5":
			String id5 = json.get("chatterId").getAsString();
			userIds.add(id5);
			jsonObject.add("userIds", userIds);
			jsonObject.addProperty("friendId", json.get("userId").getAsString());
			for (ChatServer chatServer : webSocketSet) {
				chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
			}
			System.out.println("发送给用户 " + id5 + " 信息: " + jsonObject.toString());
			break;
		case "6":
			String id6 = json.get("groupId").getAsString();
			GroupMenber groupMenber6 = new GroupMenber();
			groupMenber6.setGroupId(id6);
			GroupService groupService6 = (GroupService) SpringContextUtil.getBean("groupServiceImpl");
			List<GroupMemberDto> groupMenbers6 = groupService6.selectGroupMenber(groupMenber6);
			for (GroupMemberDto gm : groupMenbers6) {
				userIds.add(gm.getUserId());
			}
			jsonObject.add("userIds", userIds);
			jsonObject.addProperty("memberId", json.get("userId").getAsString());
			for (ChatServer chatServer : webSocketSet) {
				chatServer.session.getAsyncRemote().sendText(jsonObject.toString());
			}
			System.out.println("发送给用户 " + userIds.toString() + " 信息: " + jsonObject.toString());
			break;
		default:
			System.out.println("无效的输入");
			break;
		}
		System.out.println("--------------------");

	}

	@OnClose
	public void close() {
		System.out.println("用户" + this.session.getId() + "退出websocket");
		System.out.println("--------------------");
		webSocketSet.remove(this); // 从set中删除
	}

	@OnError
	public void error(Throwable t) {
		System.out.println("发生错误");
		System.out.println(t.toString());
		System.out.println("--------------------");
	}

}