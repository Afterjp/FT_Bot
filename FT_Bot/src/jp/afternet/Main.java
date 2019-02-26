package jp.afternet;

import java.time.Instant;
import java.util.Date;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.guild.member.UserJoinEvent;
import sx.blah.discord.handle.obj.ActivityType;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.StatusType;
import sx.blah.discord.util.EmbedBuilder;

public class Main
{
    public static IDiscordClient client;
    public static String INFO = "[INFO]: ";
	public static void main(String[] args)
	{
        Main main = new Main();
        client = new ClientBuilder().withToken("NTQwODcyMDMzOTM3MTI5NDcy.DzXOUw.Bp-dqsFOe3Ak-elPOxhPPHU8sdQ").build();
        client.getDispatcher().registerListener(main);
        client.login();
	}
    @EventSubscriber
    public void onReady(ReadyEvent e)
    {
        System.out.println(INFO + "Botを起動しました。");
        client.changePresence(StatusType.ONLINE, ActivityType.PLAYING, "/help でヘルプ表示");

    }
	@EventSubscriber
	public void onMessage(MessageReceivedEvent e)
	{
		Instant d = new Date().toInstant();
	    EmbedBuilder b = new EmbedBuilder();
	    IGuild g = e.getGuild();
		IMessage m = e.getMessage();
		IChannel c = m.getChannel();
		String M = m.getContent();
		IUser u = e.getAuthor();
		if(e.getMessage().getContent().equalsIgnoreCase("おはよう"))
		{
			e.getChannel().sendMessage("おはようございます、" + e.getAuthor().mention() + "さん！");
		}
		if(e.getMessage().getContent().equalsIgnoreCase("こんにちは"))
		{
			e.getChannel().sendMessage("こんにちは、" + e.getAuthor().mention() + "さん！");
		}
		if(e.getMessage().getContent().equalsIgnoreCase("こんばんは") || e.getMessage().getContent().equalsIgnoreCase("こんばんわ"))
		{
			e.getChannel().sendMessage("こんばんは、" + e.getAuthor().mention() + "さん！");
		}
		if(M.equalsIgnoreCase("/programmer"))
		{
			try
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(0, 200, 0)
				.withTitle("programmer権限を付与しました。")
				.withTimestamp(d);
				IRole r = e.getGuild().getRolesByName("programmer").get(0);
				u.addRole(r);
				c.sendMessage(b.build());
			}
			catch (IndexOutOfBoundsException e1)
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(200, 0, 0)
				.withTitle("programmer権限がサーバー上に見つかりません。")
				.withTimestamp(d);
				c.sendMessage(b.build());
			}
		}
		if(M.equalsIgnoreCase("/designer"))
		{
			try
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(0, 200, 0)
				.withTitle("designer権限を付与しました。")
				.withTimestamp(d);
				IRole r = e.getGuild().getRolesByName("designer").get(0);
				u.addRole(r);
				c.sendMessage(b.build());
			}
			catch (IndexOutOfBoundsException e1)
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(200, 0, 0)
				.withTitle("designer権限がサーバー上に見つかりません。")
				.withTimestamp(d);
				c.sendMessage(b.build());
			}
		}
		if(M.equalsIgnoreCase("/editor"))
		{
			try
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(0, 200, 0)
				.withTitle("editor権限を付与しました。")
				.withTimestamp(d);
				IRole r = e.getGuild().getRolesByName("editor").get(0);
				u.addRole(r);
				c.sendMessage(b.build());
			}
			catch (IndexOutOfBoundsException e1)
			{
				m.delete();
				b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
				.withAuthorName(u.getDisplayName(g) + " > " + M)
				.withColor(200, 0, 0)
				.withTitle("editor権限がサーバー上に見つかりません。")
				.withTimestamp(d);
				c.sendMessage(b.build());
			}
		}
		if(M.equalsIgnoreCase("/help"))
		{
			m.delete();
			b.withAuthorIcon(u.getAvatarURL().replaceAll("webp", "jpg"))
			.withAuthorName(u.getDisplayName(g) + " > " + M)
			.withColor(0, 200, 0)
			.withTitle("利用可能コマンド一覧")
			.appendField("/programmer", "programmer権限を付与します。", true)
			.appendField("/designer", "designer権限を付与します。", true)
			.appendField("/editor", "editor権限を付与します。", true)
			.appendField("/help", "このヘルプを表示します。", true)
			.withTimestamp(d);
			c.sendMessage(b.build());
		}
	}
	@EventSubscriber
	public void onJoin(UserJoinEvent e)
	{
		IRole r = e.getGuild().getRolesByName("member").get(0);
		e.getUser().addRole(r);
	}
}
