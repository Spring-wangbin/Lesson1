USE [master]
GO

/****** Object:  Table [dbo].[t_user]    Script Date: 03/07/2020 21:23:58 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[t_user](
	[id] [int] NULL,
	[username] [varchar](50) NULL,
	[age] [int] NULL,
	[sex] [varchar](10) NULL
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


